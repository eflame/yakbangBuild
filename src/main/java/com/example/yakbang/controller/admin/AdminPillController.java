package com.example.yakbang.controller.admin;

import com.example.yakbang.dto.admin.AdminExMemberDTO;
import com.example.yakbang.dto.admin.AdminPillDTO;
import com.example.yakbang.service.admin.AdminPillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/pill")
@RequiredArgsConstructor
public class AdminPillController {

    private final AdminPillService adminPillService;

    @GetMapping("/{itemId}")
    public ResponseEntity<List<AdminPillDTO>> findPillInfo(@PathVariable("itemId") Long itemId) {
        List<AdminPillDTO> findPillDetail = adminPillService.findPillInfo(itemId);
        return ResponseEntity.ok(findPillDetail);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePill(@RequestBody AdminPillDTO adminPillDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 일반 회원 업데이트 로직
            adminPillService.modifyPill(adminPillDTO);
            response.put("success", true);
            response.put("message", "회원 정보가 성공적으로 업데이트되었습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "업데이트 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/delete/{itemSeq}")
    public ResponseEntity<Map<String, Object>> deletePill(@PathVariable("itemSeq") Long itemSeq) {
        Map<String, Object> response = new HashMap<>();

        boolean success = adminPillService.deletePill(itemSeq) > 0;

        response.put("success", success);

        if (success) {
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "전문 회원 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
