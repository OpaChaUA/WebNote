package webnotes.model.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import webnotes.model.entity.Note;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class NoteServiceTest {
    @Autowired
    private NoteService testInstance;

    @Test
    @Rollback
    void shouldReturnListOfNotesByUserIdWhenInvoked() {
        List<Note> notes = testInstance.listAll(1);

        assertEquals(3, notes.size());
    }

    @Test
    @Rollback
    void shouldReturnNoteWithIdWhenNoteSaved() {
        Note note = Note.builder()
                .title("title")
                .content("content")
                .access("Public")
                .build();

        Note savedNote = testInstance.add(note, 1);

        assertNotNull(savedNote.getId());
    }

    @Test
    @Rollback
    void shouldDeleteNoteByIdWhenInvoked() {
        String noteToDeleteId = testInstance.listAll(1).get(0).getId();

        testInstance.deleteById(noteToDeleteId);

        assertFalse(testInstance.isNoteExist(noteToDeleteId));
    }

    @Test
    @Rollback
    void shouldUpdateNoteByIdWhenInvoked() {
        Note noteToUpdate = testInstance.listAll(1).get(0);
        noteToUpdate.setTitle("Updated title");

        testInstance.update(noteToUpdate);

        String updatedTitle = testInstance.getById(noteToUpdate.getId())
                .map(Note::getTitle)
                .orElse("");
        assertEquals("Updated title", updatedTitle);
    }

    @Test
    @Rollback
    void shouldReturnNoteByIdWhenInvoked() {
        String noteId = testInstance.listAll(1).get(0).getId();

        Optional<Note> note = testInstance.getById(noteId);

        assertTrue(note.isPresent());
    }

    @Test
    @Rollback
    void shouldCheckIfNoteExistsWhenInvoked() {
        String noteId = testInstance.listAll(1).get(0).getId();

        boolean result = testInstance.isNoteExist(noteId);

        assertTrue(result);
    }
}