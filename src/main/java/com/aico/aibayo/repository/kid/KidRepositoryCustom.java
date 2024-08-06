package com.aico.aibayo.repository.kid;

import com.aico.aibayo.dto.kid.KidDto;
import com.aico.aibayo.dto.kid.KidSearchCondition;
import java.util.List;

public interface KidRepositoryCustom {
    List<KidDto> findAllByMemberId(Long id);
    List<KidDto> findAllByClassNoAndAcceptStatus(KidSearchCondition condition);
    List<KidDto> findAllWithParentByClassNoAndAcceptStatus(KidSearchCondition condition);
    List<KidDto> findAllWithInviteByClassNoAndAcceptStatus(KidSearchCondition condition);
}
