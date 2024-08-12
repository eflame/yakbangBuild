// /js/modules/modal.js

export async function fetchData(url) {
    try {
        const response = await fetch(url, { method: 'GET' });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('데이터를 가져오는 중 오류 발생:', error);
        return null;
    }
}

export function populateModal(data, modalId) {
    if (!data) {
        console.error('데이터가 없습니다.');
        return;
    }

    const modalElement = document.getElementById(modalId);
    if (modalElement) {
        const fields = modalElement.querySelectorAll('[data-field]');
        fields.forEach(field => {
            const key = field.getAttribute('data-field');
            field.textContent = data[key] || 'N/A';
        });

        document.querySelector(".modal-wrap").classList.add("open");
        document.querySelector("body").appendChild(document.createElement('div')).classList.add('dim');
    } else {
        console.error('모달 요소를 찾을 수 없습니다.');
    }
}

export function closeModal() {
    document.querySelector(".modal-wrap")?.classList.remove("open");
    document.querySelector(".dim")?.remove();
}
