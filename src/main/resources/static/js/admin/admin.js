
{  // 게시판 옵션
    let $tblRow = document.querySelector('.tbl-row');
    let dim = document.createElement("div");
    dim.className = "dim";
    let $modalWrap = document.querySelector(".modal-wrap");

    $tblRow?.addEventListener("click", (e) => {
        if (e.target.classList.contains('btn-sm')) {
        let $btnOptions = e.target.closest('.options');
        $btnOptions.classList.add("active");
        }
        if (e.target.classList.contains('btn-open-modal')) {
            e.preventDefault();
            openModal();
        }
    });

    document.body.addEventListener('click', (e) => {
        if (e.target.classList.contains('btn-sm')) {
            return;
        }

        let $list = document.querySelectorAll('.option-box');

        $list?.forEach(ele => {
            ele.closest('.options').classList.remove('active');
        });
    });

    // 모달팝업 열기
    function openModal(){
        document.querySelector(".modal-wrap").classList.add("open");
        document.querySelector("body").appendChild(dim);
    }
    // 모달팝업 닫기
    function closeModal(){
        $modalWrap.classList.remove("open");
        dim.remove();
    }
}

{  // 탭
    let $tabs = document.querySelector(".tabs");
    let $tab = $tabs?.querySelectorAll(".item");

    $tabs?.addEventListener("click", (e) => {
        if(e.target.classList.contains('item')){
            $tab.forEach(tab => {
                tab.classList.remove('active');
            })
            e.target.classList.add('active');
        }
    })
}


{  // 정보 수정
    const $moalBody = document.querySelector(".modal-body");
    const $qnaBody = document.querySelector(".qna-body");
    const $btnArea = document.querySelector(".btn-area")
    const $btnModify = document.querySelector("#btn-modify");
    const $btnChangeModify = document.querySelector("#btn-change-modify");
    const $btnBack = document.querySelector("#btn-back");
    let oldConts = [];
    let contentOld = "";

    $btnModify?.addEventListener("click", (e) => {
        $btnArea.classList.add("modify");
        $moalBody ? memberModify(e) : null;
        if($qnaBody) {
            contentOld = $qnaBody.querySelector('.cont').innerText;
            $qnaBody.innerHTML = `
                <textarea class="textarea" name="" id="" cols="30" rows="10" >${contentOld}</textarea>
            `;
        }
    })
    $btnChangeModify?.addEventListener("click", (e) => {
        $btnArea.classList.remove("modify");
        $moalBody ? memberModify(e) : null;
        if($qnaBody) {
            let content = $qnaBody.querySelector('.textarea').value;
            $qnaBody.innerHTML = `<span class="cont">${content}</span>`;
        }
    })
    $btnBack?.addEventListener("click", (e) => {
        $btnArea.classList.remove("modify");
        $moalBody ? memberModify(e) : null;
        $qnaBody ? $qnaBody.innerHTML = `<span class="cont">${contentOld}</span>`: null;
    })

    function memberModify(e) {
        let arrInput = $moalBody.querySelectorAll('.form-control input');
        let $thisId = e.target.id;

        if ($thisId == "btn-modify") {
            arrInput.forEach((item,index) => {
                arrInput[index].readOnly = false;
                oldConts[index] = item.value;
            });
        } else if($thisId == "btn-back") {
            for(index = 0; index < arrInput.length; index++) {
                arrInput[index].readOnly = true;
                arrInput[index].value = oldConts[index];
            }
        } else if($thisId == "btn-change-modify") {
            arrInput.forEach((item,index) => {
                arrInput[index].readOnly = true;
                arrInput[index].value = item.value;
            });

            // value값 데이터 업데이트 스크립트 적용 해야됨

        }
    }
    

}
