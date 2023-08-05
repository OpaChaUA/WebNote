package webnotes.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webnotes.model.entity.Note;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, String> {
    List<Note> findByUserIdOrderByCreatedAtDesc(int userId);
}
