package webnotes.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;
import webnotes.model.entity.Note;
import webnotes.model.security.NoteValidator;
import webnotes.model.service.NoteContentFormatService;
import webnotes.model.service.NoteService;
import webnotes.model.service.UserService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {
    @Mock
    private Note note;
    @Mock
    private Principal principal;
    @Mock
    private HttpSession httpSession;
    @Mock
    private NoteService noteService;
    @Mock
    private UserService userService;
    @Mock
    private NoteValidator noteValidator;
    @Mock
    private NoteContentFormatService noteContentFormatService;

    @InjectMocks
    private NoteController testInstance;

    @Test
    void shouldReturnListPageWhenInvoked() {
        ModelAndView modelAndView = testInstance.getListOfNotes(principal, httpSession);

        assertEquals("list", modelAndView.getViewName());
    }

    @Test
    void shouldRedirectToListPageWhenOnRoot() {
        ModelAndView modelAndView = testInstance.getListOfNotesFromRoot();

        assertEquals("redirect:/note/list", modelAndView.getViewName());
    }

    @Test
    void shouldRedirectToListPageWhenInvoked() {
        ModelAndView modelAndView = testInstance.deleteNoteById("testId");

        assertEquals("redirect:/note/list", modelAndView.getViewName());
    }

    @Test
    void shouldRedirectToListPageOnGetEditWhenNoteNotExist() {
        when(noteService.getById("testId")).thenReturn(Optional.empty());

        ModelAndView modelAndView = testInstance.showEditPage("testId", httpSession);

        assertEquals("redirect:/note/list", modelAndView.getViewName());
    }

    @Test
    void shouldReturnEditPageWhenNoteExistsInDatabase() {
        when(noteService.getById("testId")).thenReturn(Optional.of(note));

        ModelAndView modelAndView = testInstance.showEditPage("testId", httpSession);

        assertEquals("edit", modelAndView.getViewName());
    }

    @Test
    void shouldReturnEditPageWhenNoteExistsInSession() {
        when(httpSession.getAttribute("note")).thenReturn(note);

        ModelAndView modelAndView = testInstance.showEditPage("testId", httpSession);

        assertEquals("edit", modelAndView.getViewName());
    }

    @Test
    void shouldRedirectToListPageOnPostEditWhenNoteNotExist() {
        ModelAndView modelAndView = testInstance.editNote(note, httpSession);

        assertEquals("redirect:/note/list", modelAndView.getViewName());
    }

    @Test
    void shouldRedirectToListPageOnPostEditWhenNoteIsValid() {
        when(note.getId()).thenReturn("testId");
        when(noteService.isNoteExist("testId")).thenReturn(true);
        when(noteValidator.isNoteValid(note)).thenReturn(Collections.emptyList());

        ModelAndView modelAndView = testInstance.editNote(note, httpSession);

        assertEquals("redirect:/note/list", modelAndView.getViewName());
    }

    @Test
    void shouldReturnErrorPageOnPostEditWhenNoteIsNotValid() {
        when(note.getId()).thenReturn("testId");
        when(noteService.isNoteExist("testId")).thenReturn(true);
        when(noteValidator.isNoteValid(note)).thenReturn(List.of("errorMessage"));

        ModelAndView modelAndView = testInstance.editNote(note, httpSession);

        assertEquals("error", modelAndView.getViewName());
    }

    @Test
    void shouldReturnCreatePageWhenNoteNotExistInSession() {
        ModelAndView modelAndView = testInstance.showCreatePage(httpSession);

        assertEquals("create", modelAndView.getViewName());
    }

    @Test
    void shouldReturnCreatePageWhenNoteExistsInSession() {
        when(httpSession.getAttribute("note")).thenReturn(note);

        ModelAndView modelAndView = testInstance.showCreatePage(httpSession);

        assertEquals("create", modelAndView.getViewName());
    }

    @Test
    void shouldRedirectToListPageOnPostCreateWhenNoteIsValid() {
        when(noteValidator.isNoteValid(note)).thenReturn(Collections.emptyList());

        ModelAndView modelAndView = testInstance.createNote(note, principal, httpSession);

        assertEquals("redirect:/note/list", modelAndView.getViewName());
    }

    @Test
    void shouldReturnErrorPageOnPostCreateWhenNoteIsNotValid() {
        when(noteValidator.isNoteValid(note)).thenReturn(List.of("errorMessage"));

        ModelAndView modelAndView = testInstance.createNote(note, principal, httpSession);

        assertEquals("error", modelAndView.getViewName());
    }

    @Test
    void shouldReturnSharePageWhenNoteIsPublic() {
        when(noteService.getById("testId")).thenReturn(Optional.of(note));
        when(note.getAccess()).thenReturn("public");

        ModelAndView modelAndView = testInstance.showSharePage("testId");

        assertEquals("share", modelAndView.getViewName());
    }

    @Test
    void shouldReturnSharePageWhenNoteIsPrivate() {
        when(noteService.getById("testId")).thenReturn(Optional.of(note));
        when(note.getAccess()).thenReturn("private");

        ModelAndView modelAndView = testInstance.showSharePage("testId");

        assertEquals("share", modelAndView.getViewName());
    }

    @Test
    void shouldReturnSharePageWhenNoteNotExist() {
        when(noteService.getById("testId")).thenReturn(Optional.empty());

        ModelAndView modelAndView = testInstance.showSharePage("testId");

        assertEquals("share", modelAndView.getViewName());
    }
}