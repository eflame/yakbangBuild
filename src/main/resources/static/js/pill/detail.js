// 쿼리스트링 가져오기
let mySearch = window.location.search
let urlSearchParams = new URLSearchParams(mySearch);
// 주소창의 id 파라미터 값만 뽑아오기
let itemSeq = urlSearchParams.get("id");


getReviews(itemSeq, 1, displayReviews);



{  // 탭
    const tabLinks = document.querySelectorAll('.tab-link');
    const tabContents = document.querySelectorAll('.tab-content');
    const reviewTab = document.querySelector('.tab-link.active');

    // tabLinks.forEach((tabLink, index) => {
    //     tabLink.addEventListener('click', () => {
    //         tabContents.forEach((tabContent, i) => {
    //             if (i === index) {
    //                 tabContent.style.display = 'block';
    //             } else {
    //                 tabContent.style.display = 'none';
    //             }
    //         });
    //
    //         tabLinks.forEach(link => {
    //             link.classList.remove('active');
    //         });
    //         tabLink.classList.add('active');
    //     });
    // });


    tabLinks.forEach((tabLink, index) => {
        tabLink.addEventListener('click', () => {
            tabContents.forEach((tabContent, i) => {
                if (i === index) {
                    tabContent.style.display = 'block';
                } else {
                    tabContent.style.display = 'none';
                }
            });

            tabLinks.forEach(link => {
                link.classList.remove('active');
            });
            tabLink.classList.add('active');

            // 리뷰 탭 클릭 시 getReviews 함수 호출
            if (tabLink === reviewTab) {
                getReviews(itemSeq, 1, displayReviews);
            }
        });
    });

    // Default tab
    if(tabContents && tabContents[0]) {
        tabContents[0].style.display = 'block';
    }
    tabLinks[0]?.classList.add('active');
}
//
// {   // 30개 더 보기 처리
//     document.querySelector('#load-more').addEventListener('click', function () {
//         let page = ++this.dataset.page;
//
//         // 쿼리 스트링 가져오기
//         const queryString = window.location.search;
//         // URLSearchParams 객체 생성 : URLSearchParams는 URL를 파싱하여 특정 파라미터를 가져올 수 있음
//         const urlParams = new URLSearchParams(queryString);
//         // keyword 파라미터만 뽑아오기
//         const keyword = urlParams.get("keyword") ?? '' // falsy면 빈문자열
//         console.log(keyword)
//
//         getPostListWithPage(keyword, page, displayPostList);
//     });
// }

function getReviews(itemSeq, page, callback) {
    fetch(`/reviews?itemSeq=${itemSeq}&page=${page}`)
        .then(response => response.json())
        .then(list => {
            console.log(list);
            if(callback) {
                callback(list);
            }
        })
        .catch(error => console.error('오류!', error));
}

function displayReviews(list){
    let $dataContainer = document.querySelector('#data-container');
    let html = ``;

    list.forEach(dto => {
        html += `
         <li class="review-content">
            <div class="user-info">
                    <span class="user-name">${dto.name}</span>
                    <span class="user-agegender">
                        ${dto.reviewAge}대 / ${dto.reviewGender === 'M' ? '남자' : '여자'}
                    </span>
            </div>
            <div class="review-info-row1">
                    <div class="rating-box">
                        <div class="rating-select" style="width: ${dto.point * 20}%; "></div>
                    </div>
            </div>
            <div class="review-full-txt">
                    ${dto.reviewContent}
            </div>
        </li>
        `;
    });


    let oldHtml = $dataContainer.innerHTML;
    $dataContainer.innerHTML = oldHtml + html;
}