/**
 * 게시물을 삭제하는 함수
 *
 * 이 함수는 `data-` 속성을 기반으로 특정 게시물을 삭제합니다.
 * 삭제할 대상의 ID와 유형을 식별하고, 서버에 삭제 요청을 전송하여 삭제 작업을 수행합니다.
 *
 * @param {HTMLElement} manager - 삭제 작업을 수행할 대상 요소. 이 요소는 `data-question-id`, `data-review-id`, `data-board-type` 속성을 통해 삭제할 항목을 식별합니다.
 *
 */
export async function deleteBoard(manager) {
    const questionId = manager.dataset.questionId;
    const reviewId = manager.dataset.reviewId;
    const boardType = manager.dataset.boardType;
    console.log(questionId);
    console.log(reviewId);
    console.log(boardType);

    const deleteUrl =
        boardType === "qna" ? `/admin/board/delete/${boardType}/${questionId}`
        : boardType === "review" ? `/admin/board/delete/${boardType}/${reviewId}`
        : null;

    try {
        const response = await fetch(deleteUrl, { method: 'DELETE' });

        if (!response.ok) {
            const errorText = await response.text();  // 오류 응답 본문 읽기
            console.error(`삭제 중 오류 발생: HTTP ${response.status} - ${errorText}`);
            throw new Error(`HTTP error! Status: ${response.status} - ${errorText}`);
        }

        const data = await response.json();
        if (data.success) {
            alert('질문 정보가 성공적으로 삭제되었습니다.');
            window.location.reload();
        } else {
            alert('삭제 실패: ' + data.message);
        }
    } catch (error) {
        console.error('삭제 중 오류 발생:', error.message);
        alert('삭제 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.');
    }

}
