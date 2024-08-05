{  // 사이드 메뉴
  const $btnMenu = document.querySelector(".btn-menu");
  const $mainMenu = document.querySelector(".main-menu");
  const $btnClose = $mainMenu?.querySelector(".btn-close");
  const dim = document.createElement("div");
  dim.className = "dim";

  $btnMenu?.addEventListener("click", () => {
    $mainMenu?.classList.add("open");
    if (document.querySelector(".all-wrap")) {
      document.querySelector(".all-wrap").appendChild(dim);
    }
  });

  $btnClose?.addEventListener("click", menuClose);
  dim?.addEventListener("click", menuClose);

  function menuClose() {
    $mainMenu.classList.remove("open");
    dim.remove();
  }
}

{  // 나이별 제일 큰값 넣기
  const $barGraph = document.querySelector(".bar-graph");
  const score = $barGraph?.getElementsByClassName("bar-graph-one");
  const $barGraphMark = document.querySelectorAll(".bar-graph-mark");
  let arrScore = new Array($barGraphMark.length);
  
  $barGraphMark.forEach( (list,index) => {
    arrScore[index] = Number(list.firstChild.innerText);
  });
  let maxScore = Math.max(...arrScore);
  let maxIndex = arrScore.indexOf(maxScore);
  if(score) {
    score[maxIndex]?.classList.add("active")
  }

}

{  // 약품상세 아코디언
  const $btnOpener = document.querySelector(".detail-info-area button.title");
  $btnOpener?.addEventListener("click", function(){
    this.closest('.detail-info-area').classList.toggle("active");
  });
}

{  // 탭
  const tabLinks = document.querySelectorAll('.tab-link');
  const tabContents = document.querySelectorAll('.tab-content');

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
    });
  });

  // Default tab
  if(tabContents && tabContents[0]) {
    tabContents[0].style.display = 'block';
  }
  tabLinks[0]?.classList.add('active');
}

{  // toastui-editor
  if(document.querySelector('#editor')){
    const editor = new toastui.Editor({
      el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
      style: 'tab' ,
      height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
      initialEditType: 'wysiwyg',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
      initialValue: '내용을 입력해 주세요.',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
      previewStyle: 'vertical',                // 마크다운 프리뷰 스타일 (tab || vertical)
      placeholder: 'Please enter text.',
    });
  }
}

{  // 약검색 select 박스
  const $searchInput = document.querySelector("#searchInput");
  const $searchSelectBox = document.querySelector(".search-select-box");
  const $keywordList = document.querySelector(".keyword-list");
  const items = [];
  // 외부 데이터 배열로 받아서 넣기
  items.push("타이젠정(쎄라티오펩티다제)","타이펜정160밀리그람(아세트아미노펜)","타이펜8시간이알서방정","타이몰8시간이알정(아세트아미노펜)","에피타이츄정")

  $searchInput?.addEventListener("keyup", () => {
    $keywordList.innerHTML = "";
    keywordItem(items);
    $searchSelectBox.querySelector(".keyword-list").classList.add("open");
  })

  $searchSelectBox?.addEventListener("click", (e) => {
    if(e.target.classList.contains("item")){
      // $keywordList = e.target.closest(".keyword-list");
      $searchInput.value = e.target.innerText;
      $keywordList.classList.remove("open");
    }
  })

  function keywordItem() {
    items.forEach((item) => {
      $keywordList.insertAdjacentHTML('afterbegin' ,`<li class="item">${item}</li>`);
    })
  }
}
{
  
  if(document.querySelector('#ckeditor')){
    ClassicEditor
    .create( document.querySelector( '#ckeditor' ), {
      language: "ko",
    } );
  }
}