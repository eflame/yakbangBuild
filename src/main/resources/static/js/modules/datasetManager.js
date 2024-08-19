
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