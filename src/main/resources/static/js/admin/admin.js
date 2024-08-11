
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
            const userId = target.dataset.member;
            const memberType = target.dataset.memberType;
            if (userId) {
                console.log('Member ID:', userId);
                console.log('Member Type:', memberType);

                if (memberType === 'expert') {
                    console.log('This is an expert ID.');
                    openModal('expert', userId);
                } else if (memberType === 'general') {
                    console.log('This is a general member ID.');
                    openModal('general', userId);
                }


            } else {
                console.error('Member ID is missing.');
            }
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

    // 모달 팝업 열기
    function openModal(memberType, userId){
        document.getElementById('modal-member-type').value = memberType;
        document.getElementById('modal-member-id').value = userId;
        fetch('/admin/members/' + memberType + '/' + userId, {
            method: 'GET'
        })
        .then(response => response.json())
        .then(data => {
            console.log('Fetched data:', data); // 데이터를 콘솔에 출력하여 확인

            if (data.length > 0) {
                const member = data[0];
                // 데이터가 포함된 객체에서 속성들을 가져와서 모달에 설정
                document.getElementById('loginId').innerText = member.loginId || 'N/A';
                document.getElementById('memberName').value = member.name || 'N/A';
                document.getElementById('memberGender').innerText = member.gender || 'N/A';
                document.getElementById('memberBirth').innerText = member.birth || 'N/A';
                document.getElementById('memberEmail').value = member.email || 'N/A';
                document.getElementById('memberPhoneNumber').value = member.phoneNumber || 'N/A';
                if (memberType === 'expert') {
                    document.getElementById('memberJob').value = member.job || 'N/A';
                    document.getElementById('pharmacyAddress').value = member.pharmacyAddress || 'N/A';
                }
                document.querySelector(".modal-wrap").classList.add("open");
                document.querySelector("body").appendChild(dim);
            } else {
                console.error("회원정보를 찾을수 없습니다..")
            }
        })
        .catch(error => {
            console.error('회원정보를 가져오는 중 오류 발생:', error)
        })
    }
    // 모달 팝업 닫기
    function closeModal(){
        $modalWrap.classList.remove("open");
        dim.remove();
    }
    // 모달 닫기 버튼 이벤트 리스너 추가 (모달 내에서 닫기 버튼이 있는 경우)
    document.querySelector('.modal-close')?.addEventListener('click', closeModal);


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
    const $btnChangeModify = document.querySelector("#btn-change-modify");
    const $btnBack = document.querySelector("#btn-back");
    let oldContents = [];
    let contentOld = "";
    let memberId = "";


    // 수정 버튼 클릭 이벤트 핸들러
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

    // 수정 완료 버튼 클릭 이벤트 핸들러
    $btnChangeModify?.addEventListener("click", (e) => {
        $btnArea.classList.remove("modify");
        memberModify(e);
        if ($qnaBody) {
            let content = $qnaBody.querySelector('.textarea').value;
            $qnaBody.innerHTML = `<span class="cont">${content}</span>`;
        }
    });

    // 취소 버튼 클릭 이벤트 핸들러
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
