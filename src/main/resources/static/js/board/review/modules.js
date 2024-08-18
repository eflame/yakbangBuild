export function searchPillName(pillName, callback){
    fetch(`/v1/pills/names?pillName=${pillName}`, {
        method : 'GET'
    }).then(resp => {
        if(resp.ok){
            return resp.json();
        }
        throw new Error('검색 리스트 통신 오류 발생')
    }).then(list => {
        if(callback) {
            callback(list);
        }
    }).catch(err => {
        console.error(err)
    });
}