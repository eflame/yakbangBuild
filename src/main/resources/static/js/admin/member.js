
// 모달 팝업 열기
// function openModal(memberType, userId){
//     document.getElementById('modal-member-type').value = memberType;
//     document.getElementById('modal-member-id').value = userId;
//
//     fetch('/admin/members/' + memberType + '/' + userId, {
//         method: 'GET'
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log('Fetched data:', data); // 데이터를 콘솔에 출력하여 확인
//
//             if (data.length > 0) {
//                 const member = data[0];
//                 // 데이터가 포함된 객체에서 속성들을 가져와서 모달에 설정
//                 document.getElementById('loginId').innerText = member.loginId || 'N/A';
//                 document.getElementById('memberName').value = member.name || 'N/A';
//                 document.getElementById('memberGender').innerText = member.gender || 'N/A';
//                 document.getElementById('memberBirth').innerText = member.birth || 'N/A';
//                 document.getElementById('memberEmail').value = member.email || 'N/A';
//                 document.getElementById('memberPhoneNumber').value = member.phoneNumber || 'N/A';
//                 if (memberType === 'expert') {
//                     document.getElementById('memberJob').value = member.job || 'N/A';
//                     document.getElementById('pharmacyAddress').value = member.pharmacyAddress || 'N/A';
//                 }
//                 document.querySelector(".modal-wrap").classList.add("open");
//                 document.querySelector("body").appendChild(dim);
//             } else {
//                 console.error("회원정보를 찾을수 없습니다..")
//             }
//         })
//         .catch(error => {
//             console.error('회원정보를 가져오는 중 오류 발생:', error)
//         })
// }
// // 모달 팝업 닫기
// function closeModal(){
//     $modalWrap.classList.remove("open");
//     dim.remove();
// }




