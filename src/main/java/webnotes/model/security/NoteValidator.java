package webnotes.model.security;

import org.springframework.stereotype.Service;
import webnotes.model.entity.Note;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteValidator {
    public List<String> isNoteValid(Note note) {
        List<String> errorMessageList = new ArrayList<>();
        if (!note.getTitle().matches("^(?!.*\\n)(?<!\\n)[^\\n]{5,100}$")) {
            errorMessageList.add("Title must be from 5 to 100 symbols. Your Title is " + note.getTitle().length() + " symbol(s)");
        }
        if (!note.getContent().matches("^[\\s\\S]{8,10000}$")) {
            errorMessageList.add("Content must be from 8 to 10000 symbols. Your Content is " + note.getContent().length() + " symbol(s)");
        }
        return errorMessageList;
    }
}
