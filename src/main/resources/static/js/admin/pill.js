
let oldContents = [];
export async function modifyMember(e) {
    // 정보 수정 함수
    let arrInput = document.querySelectorAll('.modal-body .form-control input');
    let thisId = e.target.id;

    let itemId = document.querySelector("#modal-item-id").value;
    // console.log(itemId);  // 디버깅용

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
        arrInput.forEach((item) => {
            updatedData[item.name] = item.value;
        });

        updatedData.itemSeq = itemId;
        console.log(updatedData);

        console.log('Updated Data:', updatedData);  // 디버깅용

        // 서버로 데이터 전송
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