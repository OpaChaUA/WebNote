package webnotes.model.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;
import webnotes.model.entity.Note;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteContentFormatService {

    public List<Note> getFormattedList(List<Note> notes) {
        return notes.stream()
                .map(this::getFormattedNote)
                .collect(Collectors.toList());
    }

    public Note getFormattedNote(Note note) {
        String content = note.getContent();
        Node document = Parser.builder().build().parse(content);
        String formattedContent = HtmlRenderer.builder().build().render(document);
        note.setContent(formattedContent);
        return note;
    }
}
