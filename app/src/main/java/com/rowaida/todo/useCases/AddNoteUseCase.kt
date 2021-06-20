package com.rowaida.todo.useCases

import com.rowaida.todo.data.repositories.NoteRepository
import com.rowaida.todo.data.models.Note

class AddNoteUseCase (private val noteRepository: NoteRepository) {

    operator fun invoke(note: Note) = noteRepository.addNote(note)

}