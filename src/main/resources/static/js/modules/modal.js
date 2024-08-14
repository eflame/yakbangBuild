
export async function openModal(manager) {
    try {
        // 데이터를 가져와서 모달에 로드
        await loadDataIntoModal(manager);

        // 모달 열기
        document.querySelector(".modal-wrap").classList.add("open");
        const dim = document.createElement('div');
        dim.classList.add('dim');
        document.querySelector("body").appendChild(dim);

        // 모달 열기 성공을 나타내는 메시지 반환
        return 'Modal opened successfully';

    } catch (error) {
        // 오류 발생 시 에러 메시지 반환
        throw new Error('Error opening modal: ' + error.message);
    }
}


export function closeModal() {
    // 모달 닫기
    document.querySelector(".modal-wrap")?.classList.remove("open");
    document.querySelector(".dim")?.remove();
}


async function loadDataIntoModal(manager) {
    // manager 객체에 memberId와 itemId가 있는지 확인
    const memberType = manager.dataset.memberType;
    const memberId = manager.dataset.memberId;
    const itemId = manager.dataset.itemId;

    // 데이터 세트에 따라 모달의 값을 설정
    if (memberId) {
        const modalTypeElement = document.getElementById('modal-member-type');
        const modalIdElement = document.getElementById('modal-member-id');

        modalTypeElement.value = memberType;
        modalIdElement.value = memberId;

        try {
            const response = await fetch(`/admin/members/${memberType}/${memberId}`);

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            // console.log('Fetched data:', data);

            if (data.length > 0) {
                const member = data[0];

                document.getElementById('loginId').innerText = member.loginId ?? 'N/A';
                document.getElementById('memberName').value = member.name ?? 'N/A';
                document.getElementById('memberGender').innerText = member.gender ?? 'N/A';
                document.getElementById('memberBirth').innerText = member.birth ?? 'N/A';
                document.getElementById('memberEmail').value = member.email ?? 'N/A';
                document.getElementById('memberPhoneNumber').value = member.phoneNumber ?? 'N/A';

                if (memberType === 'expert') {
                    document.getElementById('memberJob').value = member.job ?? 'N/A';
                    document.getElementById('pharmacyAddress').value = member.pharmacyAddress ?? 'N/A';
                }
            } else {
                console.error("회원정보를 찾을 수 없습니다.");
            }
        } catch (error) {
            console.error('회원정보를 가져오는 중 오류 발생:', error.message);
        }
    } else if (itemId) {
        const modalIdElement = document.getElementById('modal-item-id');
        modalIdElement.value = itemId;
    }
}


