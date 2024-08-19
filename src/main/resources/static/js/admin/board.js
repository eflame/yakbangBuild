export async function deleteBoard(manager) {
    const questionId = manager.dataset.questionId;
    const reviewId = manager.dataset.reviewId;
    const boardType = manager.dataset.boardType;
    console.log(questionId);

    const deleteUrl = boardType === "qna"
        ? `/admin/board/delete/${questionId}`
        : boardType === "review"
            ? `/admin/board/delete/${reviewId}`
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

export async function modifyBoard(e){
    // 정보 수정 함수
    let thisId = e.target.id;

    const contentOld = $qnaBody.querySelector('.textarea').value;
    $qnaBody.innerHTML = `<span class="cont" id="ansContent">${contentOld}</span>`;


    if (thisId === "btn-modify") {
        const content = $qnaBody.querySelector('.textarea').value;
        $qnaBody.innerHTML = `<textarea class="textarea" id="ansContent">${content}</textarea>`;


    } else if (thisId === "btn-back") {

    } else if (thisId === "btn-change-modify") {

    }
}