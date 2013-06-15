
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <jsp:include page="head.jsp"></jsp:include>

<body>
  <!-- header -->
  <jsp:include page="headerBar.jsp"></jsp:include>
   <script type="text/javascript" >  $('#indexjsp').addClass('current'); </script>
  
  <!-- /#gallery -->
  <div class="main-box" id="main-box">
      <jsp:include page="principalNav/IndexContent.jsp"></jsp:include>
  </div>
               
  <jsp:include page="gallerySlider.jsp"></jsp:include>
  <!-- footer -->
  <jsp:include page="footer.jsp"></jsp:include>
  
</body>
</html>