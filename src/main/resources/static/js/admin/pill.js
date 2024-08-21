/**
 * 모달팝업 수정 이벤트 처리 함수
 *
 * 이 함수는 모달 내의 입력 필드를 수정하고, 수정된 데이터만 서버로 전송합니다.
 * - '수정' 버튼을 클릭하면 입력 필드가 수정 가능 상태로 변경되며, 기존 값이 저장됩니다.
 * - '취소' 버튼을 클릭하면 수정된 내용이 원래 값으로 복원됩니다.
 * - '수정 완료' 버튼을 클릭하면 변경된 데이터만 서버에 전송합니다.
 *
 * @param {Event} e - 이벤트 객체. 클릭된 버튼을 식별하기 위해 사용됩니다.
 */
let oldContents = [];
export async function modifyMember(e) {
    // 정보 수정 함수
    let arrInput = document.querySelectorAll('.modal-body .form-control input');
    let thisId = e.target.id;

    let itemId = document.querySelector("#modal-item-id").value;

    if (thisId === "btn-modify") {
        arrInput.forEach((item, index) => {
            arrInput[index].readOnly = false;
            oldContents[index] = item.value;
        })
        console.log(arrInput);
        console.log(oldContents);
    } else if (thisId === "btn-back") {
        arrInput.forEach((item, index) => {
            arrInput[index].readOnly = true;
            arrInput[index].value = oldContents[index];
        });
        console.log(arrInput);
        console.log(oldContents);
    } else if (thisId === "btn-change-modify") {
        arrInput.forEach((item) => {
            item.readOnly = true;
        });

        // 업데이트할 데이터를 구성
        const updatedData = {};
        arrInput.forEach((item, index) => {
            if (item.value !== oldContents[index]) {
                updatedData[item.name] = item.value;
            }
        });

        updatedData.itemSeq = itemId;
        console.log(updatedData);

        console.log('Updated Data:', updatedData);  // 디버깅용

        // 변경된 데이터가 있을 때만 서버로 전송
        if (Object.keys(updatedData).length > 1) {
            let updateUrl = '/admin/pill/update';
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
}

/**
 * 약 아이템을 삭제하는 함수
 *
 * 이 함수는 `data-` 속성을 기반으로 특정 아이템을 삭제합니다.
 * 삭제할 대상의 ID와 유형을 식별하고, 서버에 삭제 요청을 전송하여 삭제 작업을 수행합니다.
 *
 * @param {HTMLElement} manager - 삭제 작업을 수행할 대상 요소. 이 요소는 `data-question-id` 속성을 통해 삭제할 항목을 식별합니다.
 *
 */
export async function deleteItem(manager) {
    const itemSeq = manager.dataset.itemId;

    if (!itemSeq) {
        console.error('User ID가 없습니다.');
        throw new Error('User ID가 없습니다.');
    }

    const deleteUrl = `/admin/pill/delete/${itemSeq}`;

    try {
        const response = await fetch(deleteUrl, { method: 'DELETE' });

        if (!response.ok) {
            const errorText = await response.text();  // 오류 응답 본문 읽기
            console.error(`삭제 중 오류 발생: HTTP ${response.status} - ${errorText}`);
            throw new Error(`HTTP error! Status: ${response.status} - ${errorText}`);
        }

        const data = await response.json();
        if (data.success) {
            alert('회원 정보가 성공적으로 삭제되었습니다.');
            window.location.reload();
        } else {
            alert('삭제 실패: ' + data.message);
        }
    } catch (error) {
        console.error('삭제 중 오류 발생:', error.message);
        alert('삭제 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.');
    }
}