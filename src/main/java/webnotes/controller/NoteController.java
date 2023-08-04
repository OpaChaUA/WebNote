package webnotes.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import webnotes.model.entity.Note;
import webnotes.model.service.NoteService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = {"/note", "/"})
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getListOfNotes() {
        return new ModelAndView("list")
                .addObject("notes", noteService.listAll());
    }

    @GetMapping("/")
    public ModelAndView getListOfNotesFromRoot() {
        return new ModelAndView("redirect:/note/list");
    }

    @PostMapping("/delete")
    public ModelAndView deleteNoteById(@RequestParam long id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView showEditPage(@RequestParam("id") long id) {
        return new ModelAndView("edit")
                .addObject("note", noteService.getById(id));
    }

    @PostMapping("/edit")
    public ModelAndView editNote(@ModelAttribute Note note)  {
        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage(HttpSession session) {
        Note note = (Note) session.getAttribute("note");
        if (note == null) {
            return new ModelAndView("create")
                    .addObject("isEmpty", true);
        }
        return new ModelAndView("create")
                .addObject("note", note)
                .addObject("isEmpty", false);

    }

    @PostMapping("/create")
    public ModelAndView createNote(@ModelAttribute Note note, Principal principal, HttpSession session)  {
        List<String> errorMessageList = noteValidator.isNoteValid(note);
        if (errorMessageList.isEmpty()) {
            int userId = userService.getIdByUsername(principal.getName());
            noteService.add(note, userId);
            return new ModelAndView("redirect:/note/list");
        }
        session.setAttribute("note", note);
        return new ModelAndView("error")
                .addObject("backLink", "/note/create")
                .addObject("errMes", errorMessageList);
    }
}
