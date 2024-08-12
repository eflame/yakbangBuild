

export function initializePagination(paginationContainer, fetchUrl) {
    // 페이지네이션 클릭 이벤트 설정
    paginationContainer.querySelectorAll('a.page-link').forEach(link => {
        link.addEventListener('click', async (event) => {
            event.preventDefault();
            const page = new URL(link.href).searchParams.get('page');
            try {
                const response = await fetch(`${fetchUrl}?page=${page}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok.');
                }
                const html = await response.text();
                document.querySelector('#content').innerHTML = html;
            } catch (error) {
                console.error('Error fetching page:', error);
            }
        });
    });
}