import { DatasetManager } from '../modules/datasetManager.js';
import * as modal  from '../modules/modal.js';
import * as pageNation from '../modules/pagination.js';
import * as search from  '../modules/search.js';


document.addEventListener('DOMContentLoaded', () => {
    // DOM 요소 선택
    const $tblRow = document.querySelector('.tbl-row');
    const dim = document.createElement("div");
    dim.className = "dim";
    const $modalWrap = document.querySelector(".modal-wrap");

    // 게시판 옵션 클릭 처리
    if ($tblRow) {
        $tblRow.addEventListener("click", (e) => {
            handleRowClick(e);
        });
    }

    // 클릭 처리 함수
    function handleRowClick(event) {
        const target = event.target;

        // 'btn-sm' 클래스가 있는 버튼 클릭 처리
        if (target.classList.contains('btn-sm')) {
            // 모든 .options 요소 비활성화
            document.querySelectorAll('.options').forEach(option => {
                option.classList.remove("active");
            });

            // 클릭된 .options 요소 활성화
            const $btnOptions = target.closest('.options');
            if ($btnOptions) {
                $btnOptions.classList.add("active");
            }
        }

        // 'btn-open-modal' 클래스가 있는 버튼 클릭 처리
        if (target.classList.contains('btn-open-modal')) {
            event.preventDefault();
            const manager = new DatasetManager(event.target)

            if (manager.dataset.hasOwnProperty('memberId')) {
                console.log('Member ID:', manager.dataset.memberId);
                console.log('Member Type:', manager.dataset.memberType);

                if (manager.dataset.memberType === 'expert') {
                    console.log('This is an expert ID.');
                    modal.openModal(manager).then(result => {
                        console.log('Modal opened successfully:', result);
                    }).catch(error => {
                        console.error('Error opening modal:', error);
                    });
                } else if (manager.dataset.memberType === 'general') {
                    console.log('This is a general member ID.');
                    modal.openModal(manager).then(result => {
                        console.log('Modal opened successfully:', result);
                    }).catch(error => {
                        console.error('Error opening modal:', error);
                    });
                }
            } else {
                console.error('Member ID is missing.');
            }
        }


        if (target.classList.contains('btn-deleted')) {
            event.preventDefault();
            const manager = new DatasetManager(event.target)

            const userId = manager.dataset.memberId;
            const memberType = target.dataset.memberType;
            if (userId) {
                console.log('Member ID:', userId);
                console.log('Member Type:', memberType);
            }


            // 서버로 데이터 전송
            let deleteUrl = memberType === "expert" ? '/admin/members/deleteExpert/' + userId : '/admin/members/deleteGeneral/' + userId;
            console.log(deleteUrl);

            fetch(deleteUrl, {
                method: 'DELETE',
            })
            .then(response => {
                return response.json();  // JSON 형식으로 파싱
            })
            .then(data => {
                if (data.success) {
                    alert('회원 정보가 성공적으로 삭제되었습니다.');
                    window.location.reload();
                } else {
                    alert('삭제 실패: ' + data.message);
                }
            })
            .catch(error => {
                console.error('삭제 중 오류 발생:', error);
            });
        }
    }

    // 문서 전체 클릭 처리 (옵션 메뉴 닫기)
    document.body.addEventListener('click', (e) => {
        if (e.target.classList.contains('btn-sm')) {
            return;
        }

        let $list = document.querySelectorAll('.option-box');

        $list?.forEach(ele => {
            ele.closest('.options').classList.remove('active');
        });
    });


    // 모달 닫기 버튼 이벤트 리스너 추가 (모달 내에서 닫기 버튼이 있는 경우)
    document.querySelector('.modal-close')?.addEventListener('click', modal.closeModal);


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

    // 정보 수정

    // DOM 요소 선택
    const $modalBody = document.querySelector(".modal-body");
    const $qnaBody = document.querySelector(".qna-body");
    const $btnArea = document.querySelector(".btn-area");
    const $btnModify = document.querySelector("#btn-modify");
    const $btnCheck = document.querySelector("#btn-check");
    const $btnChangeModify = document.querySelector("#btn-change-modify");
    const $btnBack = document.querySelector("#btn-back");
    let oldContents = [];
    let contentOld = "";


    // 수정 버튼
    $btnModify?.addEventListener("click", (e) => {
        $btnArea.classList.add("modify");
        memberModify(e);
        if ($qnaBody) {
            contentOld = $qnaBody.querySelector('.cont').innerText;
            $qnaBody.innerHTML = `
                <textarea class="textarea" cols="30" rows="10">${contentOld}</textarea>
            `;
        }
    });

    // 확인 버튼
    $btnCheck?.addEventListener('click', modal.closeModal);

    // 수정완료 버튼
    $btnChangeModify?.addEventListener("click", (e) => {
        $btnArea.classList.remove("modify");
        memberModify(e);
        if ($qnaBody) {
            let content = $qnaBody.querySelector('.textarea').value;
            $qnaBody.innerHTML = `<span class="cont">${content}</span>`;
        }
    });

    // 취소 버튼
    $btnBack?.addEventListener("click", (e) => {
        $btnArea.classList.remove("modify");
        memberModify(e);
        if ($qnaBody) {
            $qnaBody.innerHTML = `<span class="cont">${contentOld}</span>`;
        }
    });

    // 정보 수정 함수
    function memberModify(e) {
        let arrInput = $modalBody.querySelectorAll('.form-control input');
        let thisId = e.target.id;

        const memberType = document.querySelector("#modal-member-type").value;
        let userId = document.querySelector("#modal-member-id").value;
        console.log(memberType);  // 디버깅용
        console.log(userId);  // 디버깅용

        if (thisId === "btn-modify") {
            arrInput.forEach((item, index) => {
                arrInput[index].readOnly = false;
                oldContents[index] = item.value;
            });
        } else if (thisId === "btn-back") {
            arrInput.forEach((item, index) => {
                arrInput[index].readOnly = true;
                arrInput[index].value = oldContents[index];
            });
        } else if (thisId === "btn-change-modify") {
            arrInput.forEach((item) => {
                item.readOnly = true;
            });

            // 업데이트할 데이터를 구성
            const updatedData = {};
            arrInput.forEach((item) => {
                updatedData[item.name] = item.value;
            });
            if (memberType === "expert") {
                updatedData.expertId = userId;
            } else {
                updatedData.memberId = userId;
            }

            console.log('Updated Data:', updatedData);  // 디버깅용

            // 서버로 데이터 전송
            let updateUrl = memberType === "expert" ? '/admin/members/updateExpert' : '/admin/members/updateGeneral';
            fetch(updateUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedData)
            })
            .then(response => {
                // 응답 상태를 먼저 확인
                if (!response.ok) {
                    throw new Error('네트워크 응답이 실패했습니다.');
                }

                // 응답이 JSON인지 확인
                const contentType = response.headers.get('Content-Type');
                if (contentType && contentType.includes('application/json')) {
                    return response.json();  // JSON으로 파싱
                } else {
                    return response.text().then(text => {
                        throw new Error('응답 형식이 JSON이 아닙니다: ' + text);
                    });
                }
            })
            .then(data => {
                console.log('Update response:', data);
                if (data.success) {
                    alert('회원 정보가 성공적으로 업데이트되었습니다.');
                    closeModal();
                    // 페이지 새로고침
                    window.location.reload();
                } else {
                    alert('업데이트 실패: ' + data.message);
                }
            })
            .catch(error => {
                console.error('업데이트 중 오류 발생:', error);
            });
        }
    }

    document.querySelectorAll('.tabs .item').forEach(item => {
        item.addEventListener('click', function(e) {
            const urlParams = new URLSearchParams(window.location.search);
            const memberType = this.getAttribute('data-member-type');

            urlParams.set('memberType', memberType);
            const newUrl = `${window.location.pathname}?${urlParams.toString()}`;

            window.location.href = newUrl;
        });
    });

});
