getList(1, displayList);

{
    let $loadMoreBtn = document.querySelector('#load-more');

    $loadMoreBtn.addEventListener('click', function () {
        let page = ++this.dataset.page;
        console.log(page)
        getList(page, displayList);
    });
}

function getList(page, callback) {
    fetch(`/v1/pills?page=${page}`, {
        method : 'get'
    }).then(resp => {
        if (resp.ok) { return resp.json(); }
        else { throw new Error('약 정보 리스트 통신 에러')}
    }).then(list => {
        // console.log(list);
        callback(list);
    }).catch(err => console.error(err))
}

function displayList(list) {
    let $listBox = document.querySelector('.pill-list');

    let html = '';

    list.forEach(pill => {
        html += `
        <a href="/pill/detail?id=${pill.itemSeq}"
           class="pill-item">
            <!-- 약 이미지 -->
            <img src="${pill.pillImage}"
                 alt="Pill Image"
                 class="pill-img" />
            <div class="info">
                <!-- 브랜드 (제조사) -->
                <span class="brand">${pill.companyName}</span>
                <!-- 약 이름 -->
                <span class="pill-name">${pill.pillName}</span>
                <!-- 리뷰 점수 (예시로 고정된 값 사용) -->
                <span class="review-score">4.68</span>
            </div>
        </a>
        `;
    });

    let oldHtml = $listBox.innerHTML;
    $listBox.innerHTML = oldHtml + html;

}










