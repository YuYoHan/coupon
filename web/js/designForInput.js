 const searchContainer = document.getElementById('searchContainer');
 const searchInput = document.getElementById('searchInput');
 searchInput.onfocus = () =>{
   readyInput();
 }
 searchInput.onblur = () =>{
   unReadyInput();
 }
 const readyInput = () =>{
   searchInput.classList.remove('searchInput');
   searchContainer.classList.add('focusContainer');
   searchInput.classList.add('focusInput');
   searchInput.removeAttribute("placeholder");
 }
 const unReadyInput = () =>{
   searchInput.classList.remove('focusInput');
   searchContainer.classList.remove('focusContainer');
   searchInput.classList.add('searchInput');
   searchInput.setAttribute("placeholder","도시나 상품을 검색해보세요");
 }