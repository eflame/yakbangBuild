import { DatasetManager } from '../modules/datasetManager.js';
import * as modal  from '../modules/modal.js';
import {
    handleModifyClick,
    handleCheckClick,
    handleChangeModifyClick,
    handleBackClick
} from '../modules/modalEventHandlers.js';
import  * as member from './member.js';
import  * as pill from './pill.js';
import  * as board from './board.js';

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

        // view 버튼
        if (target.classList.contains('btn-open-modal')) {
            event.preventDefault();
            const manager = new DatasetManager(event.target)

            modal.openModal(manager)
            .then(result => {
                console.log('Modal opened successfully:', result);
            })
            .catch(error => {
                console.error('Error opening modal:', error);
            });
        }

        // deleted 버튼
        if (target.classList.contains('btn-deleted')) {
            event.preventDefault();
            const manager = new DatasetManager(event.target)
            const userId = manager.dataset.memberId;
            const itemId = manager.dataset.itemId;
            const questionId = manager.dataset.questionId;

            console.log(manager.dataset.questionId);
            if (userId) {
                // 회원 삭제 처리
                member.deleteMember(manager)
                    .then(() => {
                        console.log('삭제 작업이 성공적으로 완료되었습니다.');
                    })
                    .catch((error) => {
                        console.error('삭제 작업 중 오류 발생:', error);
                    });
            } else if (itemId) {
                console.log("아이템 클릭");
                // 상품 삭제 처리
                pill.deleteItem(manager)
                    .then(() => {
                    console.log('삭제 작업이 성공적으로 완료되었습니다.');
                })
                    .catch((error) => {
                        console.error('삭제 작업 중 오류 발생:', error);
                    });
            } else if (questionId) {
                // 게시판 항목 삭제 처리
                board.deleteBoard(manager)
                    .then(() => {
                        console.log('삭제 작업이 성공적으로 완료되었습니다.');
                    })
                    .catch((error) => {
                        console.error('삭제 작업 중 오류 발생:', error);
                    });
            } else {
                console.error('삭제할 항목이 지정되지 않았습니다.');
            }

        }
    }

    // 문서 전체 클릭 처리 (옵션 메뉴 닫기)
    document.body.addEventListener('click', (e) => {
        if (e.target.classList.contains('btn-sm')) { return; }
        let $list = document.querySelectorAll('.option-box');

        $list?.forEach(ele => {
            ele.closest('.options').classList.remove('active');
        });
    });


    // 모달 닫기 버튼 이벤트 리스너 추가 (모달 내에서 닫기 버튼이 있는 경우)
    document.querySelector('.modal-close')?.addEventListener('click', modal.closeModal);

    // 정보 수정
    const $btnModify = document.querySelector("#btn-modify");
    const $btnCheck = document.querySelector("#btn-check");
    const $btnChangeModify = document.querySelector("#btn-change-modify");
    const $btnBack = document.querySelector("#btn-back");

    $btnModify?.addEventListener("click", handleModifyClick);
    $btnCheck?.addEventListener('click', handleCheckClick);
    $btnChangeModify?.addEventListener("click", handleChangeModifyClick);
    $btnBack?.addEventListener("click", handleBackClick);

});
