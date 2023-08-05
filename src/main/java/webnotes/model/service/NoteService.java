package webnotes.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webnotes.model.entity.Note;
import webnotes.model.repository.NoteRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll(int userId) {
        return noteRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Note add(Note note, int userId) {
        note.setUserId(userId);

        return noteRepository.save(note);
    }

    public void deleteById(String id) {
        getById(id).ifPresent(noteRepository::delete);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }

    public Optional<Note> getById(String id) {
        return noteRepository.findById(id);
    }

    public boolean isNoteExist(String id) {
        return getById(id).isPresent();
    }
}
