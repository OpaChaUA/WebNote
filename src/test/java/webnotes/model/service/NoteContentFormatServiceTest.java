package webnotes.model.service;

import org.junit.jupiter.api.Test;
import webnotes.model.entity.Note;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteContentFormatServiceTest {
    private final NoteContentFormatService testInstance = new NoteContentFormatService();

    @Test
    void getFormattedList() {
        Note note = Note.builder()
                .title("Title")
                .content("Content")
                .access("Public")
                .build();
        List<Note> notes = List.of(note);

        List<Note> formattedList = testInstance.getFormattedList(notes);

        assertEquals("<p>Content</p>\n", formattedList.get(0).getContent());
    }
}