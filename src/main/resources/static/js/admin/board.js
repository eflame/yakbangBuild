export async function deleteBoard(manager) {


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