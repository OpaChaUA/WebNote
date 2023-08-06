package webnotes.model.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import webnotes.model.entity.Note;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteValidatorTest {
    @Mock
    private Note note;

    @InjectMocks
    private NoteValidator testInstance;

    @Test
    void shouldReturnEmptyErrorListWhenNoteIsValid() {
        when(note.getTitle()).thenReturn("12345");
        when(note.getContent()).thenReturn("12345");

        List<String> errorMessageList = testInstance.isNoteValid(note);

        assertTrue(errorMessageList.isEmpty());
    }

    @Test
    void shouldReturnListWithErrorsWhenNoteIsNotValid() {
        when(note.getTitle()).thenReturn("1234");
        when(note.getContent()).thenReturn("1234");

        List<String> errorMessageList = testInstance.isNoteValid(note);

        assertEquals(2, errorMessageList.size());
    }
}