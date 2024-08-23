// 쿼리스트링 가져오기
let mySearch = window.location.search
let urlSearchParams = new URLSearchParams(mySearch);
// 주소창의 id 파라미터 값만 뽑아오기
let itemSeq = urlSearchParams.get("id");


getReviews(itemSeq, 1, displayReviews);

{ // 탭
    const tabLinks = document.querySelectorAll('.tab-link');
    const tabContents = document.querySelectorAll('.tab-content');

    tabLinks.forEach((tabLink, index) => {
        tabLink.addEventListener('click', () => {
            tabContents.forEach((tabContent, i) => {
                tabContent.style.display = i === index ? 'block' : 'none';
            });

            tabLinks.forEach(link => link.classList.remove('active'));
            tabLink.classList.add('active');

            // 리뷰 탭 클릭 시 getReviews 함수 호출
            if (tabLink.classList.contains('review-tab')) {
                getReviews(itemSeq, 1, displayReviews);
            }
        });
    });

    // 초기 기본 탭 설정
    if (tabContents[0]) {
        tabContents[0].style.display = 'block';
        tabLinks[0]?.classList.add('active');
    }
}

// 리뷰 데이터
function getReviews(itemSeq, page, callback) {
    fetch(`/reviews?itemSeq=${itemSeq}&page=${page}`)
        .then(response => response.json())
        .then(list => {
            console.log(list);
            if (callback) {
                callback(list);
            }
            DisplayAverageRating(list);
        })
        .catch(error => console.error('오류 발생!', error));
}


// 평점
function DisplayAverageRating(reviews) {
    if (reviews.length === 0) {
        document.querySelector('.rating').textContent = "0.0";
        document.querySelector('.rating-select').style.width = "0%";
        return;
    }

    const totalPoints = reviews.reduce((sum, review) => sum + review.point, 0);
    const averageRating = totalPoints / reviews.length;
    const averageRatingRounded = averageRating.toFixed(2);

    // 평균 평점 표시
    document.querySelector('.rating').textContent = averageRatingRounded;
    // document.querySelector('.rating-select').style.width = `calc(${(averageRating - 1) / 4 * 100}%)`;
    const starWidthPercentage = (averageRating / 5) * 100;
    document.querySelector('.rating-select').style.width = `${starWidthPercentage}%`;

    // 개별 리뷰 표시
    displayReviews(reviews);
}


// 그래프
function displayReviews(list) {
    let $dataContainer = document.querySelector('#data-container');
    let html = ``;

    let maleCount = 0;
    let femaleCount = 0;
    let ageDistribution = {
        "10대": 0,
        "20대": 0,
        "30대": 0,
        "40대": 0,
        "50대": 0,
        "60대": 0
    };

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
                        <div class="rating-select" style="width: ${dto.point * 20}%;"></div>
                    </div>
                </div>
                <div class="review-full-txt">
                    ${dto.reviewContent}
                </div>
            </li>
        `;

        // 성별 카운트
        if (dto.reviewGender === 'M') {
            maleCount++;
        } else {
            femaleCount++;
        }

        // 연령대 카운트
        if (ageDistribution[`${dto.reviewAge}대`] !== undefined) {
            ageDistribution[`${dto.reviewAge}대`]++;
        }
    });

$dataContainer.innerHTML += html;

displayReviewCount(list.length);


// 성별
const totalCount = maleCount + femaleCount;
const malePercentage = totalCount > 0 ? (maleCount / totalCount) * 100 : null;
const femalePercentage = totalCount > 0 ? (femaleCount / totalCount) * 100 : null;

const maleText = malePercentage !== null ? `${malePercentage.toFixed(1)}%` : "0.0%";
const femaleText = femalePercentage !== null ? `${femalePercentage.toFixed(1)}%` : "0.0%";

document.querySelector('.male-gauge .gauge').style.height = malePercentage !== null ? `${malePercentage}%` : "0.0%";
document.querySelector('.male-gauge .num').textContent = maleText;

document.querySelector('.female-gauge .gauge').style.height = femalePercentage !== null ? `${femalePercentage}%` : "0.0%";
document.querySelector('.female-gauge .num').textContent = femaleText;

// 연령대 바 그래프 최대값 찾기
const maxAgeGroupCount = Math.max(...Object.values(ageDistribution));
Object.keys(ageDistribution).forEach(ageGroup => {
    const ageGroupElement = document.querySelector(`.bar-graph-one[data-agegroup="${ageGroup}"]`);
    const count = ageDistribution[ageGroup];
    const percentage = (count / maxAgeGroupCount) * 100;
    ageGroupElement.querySelector('.bar-graph-bar').style.width = `${percentage}%`;
    ageGroupElement.querySelector('.bar-graph-mark span').textContent = count;

    // 밀집도가 가장 높은 그래프에 박스 표시
        if (count === maxAgeGroupCount) {
            ageGroupElement.classList.add('active');
        } else {
            ageGroupElement.classList.remove('active');
        }
    });
}


// 리뷰 개수
function displayReviewCount(count) {
    let reviewCountElement = document.querySelector('.review-cnt');
    reviewCountElement.textContent = `리뷰 ${count}개`;
}
