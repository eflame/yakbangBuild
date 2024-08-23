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
        list.forEach(pill => {
            // 각 약품의 평점을 개별적으로 가져오기
            fetch(`/reviews?itemSeq=${pill.itemSeq}&page=1`)
                .then(response => response.json())
                .then(reviews => {
                    updatePillRating(pill.itemSeq, reviews);
                })
                .catch(err => console.error(`Error fetching reviews for itemSeq ${pill.itemSeq}:`, err));
        });

        callback(list);
    }).catch(err => console.error(err))
}

function updatePillRating(itemSeq, reviews) {
    const totalPoints = reviews.reduce((sum, review) => sum + review.point, 0);
    const averageRating = reviews.length > 0 ? (totalPoints / reviews.length).toFixed(2) : '0.00';

    // 해당 아이템 요소 찾아서 변경
    const pillElement = document.querySelector(`.pill-item[href*="id=${itemSeq}"]`);

    if (pillElement) {
        const reviewScoreElement = pillElement.querySelector('.review-score');

        if (reviewScoreElement) {
            reviewScoreElement.textContent = averageRating;
        }
    }
}

function displayList(list) {
    let $listBox = document.querySelector('.pill-list');
    let html = '';

    list.forEach(pill => {
        html += `
        <a href="/pill/detail?id=${pill.itemSeq}"
           class="pill-item">
            <!-- 약 이미지 -->
            <img src="${ pill.pillImage != null ? pill.pillImage : '/images/common/img_pill_03.png' }" 
                 style=" ${ pill.pillImage != null ? '' : 'width: 70px' }"
                 alt="Pill Image"
                 class="pill-img" 
                 />
            <div class="info">
                <!-- 브랜드 (제조사) -->
                <span class="brand">${pill.companyName}</span>
                <!-- 약 이름 -->
                <span class="pill-name">${pill.pillName}</span>
                <!-- 리뷰 점수 -->
                <span class="review-score">0.00</span>
            </div>
        </a>
        `;
    });

    let oldHtml = $listBox.innerHTML;
    $listBox.innerHTML = oldHtml + html;
}




