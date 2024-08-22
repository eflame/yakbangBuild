/**
 * `data-`속성을 보다 쉽게 관리할 수 있게 해주는 클래스입니다.
 *
 * 이 클래스는 HTML 요소의 `data-` 속성을 읽고, 설정하며, 모든 `data-` 속성을 객체 형태로 가져오는 기능을 제공합니다.
 */
export class DatasetManager {
    constructor(element) {
        this.element = element;
        this.dataset = element.dataset;
    }

    get(key) {
        return this.dataset[key];
    }

    set(key, value) {
        this.dataset[key] = value;
    }

    getAll() {
        return Object.fromEntries(Object.entries(this.dataset));
    }
}