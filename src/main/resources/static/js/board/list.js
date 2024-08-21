{   // 10개 더 보기 처리
    document.querySelector('#load-more').addEventListener('click', function () {
        let page = ++this.dataset.page;

        // 쿼리 스트링 가져오기
        const queryString = window.location.search;
        // URLSearchParams 객체 생성 : URLSearchParams는 URL를 파싱하여 특정 파라미터를 가져올 수 있음
        const urlParams = new URLSearchParams(queryString);
        // keyword 파라미터만 뽑아오기
        const keyword = urlParams.get("keyword") ?? '' // falsy면 빈문자열
        console.log(keyword)

        getPostListWithPage(keyword, page, displayPostList);
    });
}

/**
 * @param {string} keyword - 검색어
 * @param {number} page - 페이지 번호
 * @param {function} callback - 불러온 리스트를 처리할 콜백함수(매개변수로 list를 받아옴)
 */
function getPostListWithPage(keyword, page, callback) {
    fetch(`/v1/question?keyword=${keyword}&page=${page}`, {
        method: 'GET'
    }).then(resp => {
        if (resp.ok) {
            return resp.json();
        }

        throw new Error('리스트 목록 통신 오류');
    }).then(list => {
        if (callback) {
            callback(list);
        }
    }).catch(err => console.error(err));
}

function displayPostList(list) {
    let html = ``;

    list.forEach(board => {
        html += `
       <div class="qna-card">
            <a tabindex="0" class="qna-main" title="이 Q&amp;A의 상세정보 보기" href="/board/qna-detail?questionId=${board.questionId}">
               <span class="txt1"> <span class="qmark">Q.&nbsp; </span> ${board.title}</span>
               <span class="txt2">${board.content}</span>
            </a>
            <a tabindex="0" class="qna-user-wrap" title="이 Q&amp;A의 상세정보 보기" href="/board/qna-detail?questionId=${board.questionId}">
               ${board.answerContent ? '<div class="qna-user-icon-1"></div>': ''}
               <div class="qna-user-txt">
                    ${board.answerContent == null ? '' : board.answerContent}
               </div>
                  <div class="user-info-row">
                      <span class="user-name">${board.name}</span>
                      <span class="user-agesex">
                           ${board.age}대 / ${board.gender == 'M' ? '남자' : '여자'}
                      </span>
                      <span class="img-icon">
                           ${board.hasImage == 1 ? ' / 사진 있음' : ''}
                      </span>
                      <span>
                           ${board.answerContent == null ? '' : ' / 답변완료'}
                      </span>
                  </div>
            </a>
       </div>
       `;
    });

    document.querySelector('.qna-container-wrap').insertAdjacentHTML('beforeend', html);
}