
export async function openModal(manager) {
    console.log('Opening modal with data:', manager);
    // 데이터를 가져와서 모달에 로드
    await loadDataIntoModal(manager);

    // 모달 열기
    document.querySelector(".modal-wrap").classList.add("open");
    const dim = document.createElement('div');
    dim.classList.add('dim');
    document.querySelector("body").appendChild(dim);
}

export function closeModal() {
    // 모달 닫기
    document.querySelector(".modal-wrap")?.classList.remove("open");
    document.querySelector(".dim")?.remove();
}


async function loadDataIntoModal(manager) {
    const modalTypeElement = document.getElementById('modal-member-type');
    const modalIdElement = document.getElementById('modal-member-id');

    if (!modalTypeElement || !modalIdElement) {
        console.error('모달 요소를 찾을 수 없습니다.');
        return;
    }

    modalTypeElement.value = manager.dataset.memberType;
    modalIdElement.value = manager.dataset.memberId;

    const memberType = manager.dataset.memberType;
    const userId = manager.dataset.memberId;

    try {
        const response = await fetch(`/admin/members/${memberType}/${userId}`);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log('Fetched data:', data);

        if (data.length > 0) {
            const member = data[0];

            // Optional Chaining and Nullish Coalescing Operator
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
        console.error('회원정보를 가져오는 중 오류 발생:', error);
    }
}


