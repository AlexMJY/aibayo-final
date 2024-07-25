package com.aico.aibayo.service.notepad;

import com.aico.aibayo.dto.notepad.NotepadDto;
import com.aico.aibayo.dto.notepad.NotepadSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface NotepadService {
    Page<NotepadDto> getAllByKinderNo(NotepadSearchCondition condition, int page);
    Page<NotepadDto> getAllByKidNo(NotepadSearchCondition condition, int page);
    NotepadDto getByNotepadNo(Long notepadNo);
    NotepadDto insertNotepad(NotepadDto notepadDto);
}
