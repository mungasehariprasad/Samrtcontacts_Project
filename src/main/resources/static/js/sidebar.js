// const toggleSidebar=()=>{
//     if($(".sidebar").is(":visible")){
//         $(".sidebar").css("display","none");
//         $(".content").css("margin-left","0%");

//     }else{
//         $(".sidebar").css("display","block");
//         $(".content").css("margin-left","20%");


//     }
// };

// const to=()=>{
//     document.getElementById('sidebarCollapse').addEventListener('click', function () {
//         document.getElementById('sidebar').classList.toggle('active');
//       });
// }
function toggleSidebar() {
  const sidebar = document.getElementById('sidebar');
  const content = document.getElementById('content');

  if (sidebar.style.left === '0px') {
    sidebar.style.left = '-250px';
    content.style.marginLeft = '0';
  } else {
    sidebar.style.left = '0';
    content.style.marginLeft = '250px';
  }
}

function closeSidebar() {
  const sidebar = document.getElementById('sidebar');
  const content = document.getElementById('content');

  sidebar.style.left = '-250px';
  content.style.marginLeft = '0';
}
