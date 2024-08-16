import * as md from './modules.js';

{ // 엔터 서브밋 막기
    document.addEventListener('keydown', function (e) {
        if (e.code === 'Enter') {
            e.preventDefault();
        }
    });
}

{ // 검색 통신 처리
    const $searchInput = document.querySelector('#searchInput')

    $searchInput.addEventListener('keyup', function () {
        // console.log('up!!')
        // console.log(this.value)
        let inputValue = this.value;

        md.searchPillName(inputValue, list => createSelectBox(list));

    });
}

/**
 * 검색후 셀렉트 박스를 화면에 렌더링하는 함수
 *
 * @param itemList : Array<Object> - 화면에 뿌릴 데이터 객체 리스트(pillName, pillId)
 */
function createSelectBox(itemList) {  // 약검색 select 박스
    const $searchInput = document.querySelector("#searchInput");
    const $searchSelectBox = document.querySelector(".search-select-box");
    const $keywordList = document.querySelector(".keyword-list");
    const items = [];
    // 외부 데이터 배열로 받아서 넣기
    items.push(...itemList)

    $keywordList.innerHTML = "";
    keywordItem(items);
    $searchSelectBox.querySelector(".keyword-list").classList.add("open");

    $searchSelectBox?.addEventListener("click", (e) => {
        if (e.target.classList.contains("item")) {
            // $keywordList = e.target.closest(".keyword-list");
            $searchInput.value = e.target.innerText;
            $keywordList.classList.remove("open");

            document.querySelector('#pillId').value = e.target.dataset.id;
        }
    });

    function keywordItem(itemList) {
        itemList.forEach((item) => {
            $keywordList.insertAdjacentHTML('afterbegin', `<li class="item" data-id="${item.pillId}">${item.pillName}</li>`);
        });
    }
}

{ // 에디터 처리

    const toolbar = [
        ['heading', 'bold', 'italic', 'strike'],
        ['hr', 'quote'],
        ['ul', 'ol', 'task', 'indent', 'outdent'],
        ['table', 'image', 'link'],
        ['code', 'codeblock'],
        ['scrollSync'],
    ]

    const editor = new toastui.Editor({
        el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
        style: 'tab',
        height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
        initialEditType: 'wysiwyg',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
        previewStyle: 'vertical',                // 마크다운 프리뷰 스타일 (tab || vertical)
        placeholder: '내용을 입력해주세요.',
        // hideModeSwitch: 'false',
        toolbarItems: toolbar,
        /* start of hooks */
        hooks: {
            async addImageBlobHook(blob, callback) { // 이미지 업로드 로직 커스텀
                try {
                    /*
                     * 1. 에디터에 업로드한 이미지를 FormData 객체에 저장
                     *    (이때, 컨트롤러 uploadEditorImage 메서드의 파라미터인 'image'와 formData에 append 하는 key('image')값은 동일해야 함)
                     */
                    const formData = new FormData();
                    formData.append('image', blob);

                    // 2. FileApiController - uploadEditorImage 메서드 호출
                    const response = await fetch('/tui-editor/image-upload', {
                        method: 'POST',
                        body: formData,
                    });

                    // 3. 컨트롤러에서 전달받은 디스크에 저장된 파일명
                    const filename = await response.text();
                    console.log('서버에 저장된 파일명 : ', filename);

                    // 4. addImageBlobHook의 callback 함수를 통해, 디스크에 저장된 이미지를 에디터에 렌더링
                    const imageUrl = `/tui-editor/image-print?filename=${filename}`;
                    callback(imageUrl, 'image alt attribute');

                } catch (error) {
                    console.error('업로드 실패 : ', error);
                }
            }
        }
        /* end of hooks */

    });

    // form 서브밋 처리
    const $writeBtn = document.querySelector('#write-btn');

    $writeBtn.addEventListener('click', function () {
        let contentHtml = editor.getHTML();
        let form = document.forms[0];

        console.dir(form)

        let contentField = document.createElement("textarea");
        contentField.setAttribute("name", "reviewContent");
        contentField.innerText = contentHtml;
        form.appendChild(contentField);
        // document.body.appendChild(form);

        form.submit();
    });


}


