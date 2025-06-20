document.addEventListener('DOMContentLoaded', function() {
    const textarea = document.querySelector('.form-control.auto-height')

    function adjustTextareaHeight(el) {
        el.style.height = 'auto'; // 높이 초기화
        el.style.height = el.scrollHeight + 'px'; // 스크롤 높이로 설정
    }

    // 초기 로드 시 높이 조절 (이미 내용이 있는 경우)
    adjustTextareaHeight(textarea);

    // 입력 시 높이 조절
    textarea.addEventListener('input', function() {
        adjustTextareaHeight(this);
    });

    // (선택 사항) 창 크기 조절 시에도 높이 조절 (텍스트 줄바꿈 변경 시)
    window.addEventListener('resize', function() {
        adjustTextareaHeight(textarea);
    });
});