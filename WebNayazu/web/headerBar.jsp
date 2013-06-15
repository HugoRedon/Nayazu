<%-- 
    Document   : headerBar
    Created on : 06-mar-2013, 23:27:39
    Author     : Chilpayate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

  <!-- header -->
  <header>
      <script type="text/javascript">
          $(document).ready(function(){
              $('#topNavigation a').click(function (event){          
                       event.preventDefault();
                        $('#topNavigation a').removeClass('current');
                         $(this).addClass('current');                
                         var addressValue = $(this).attr("href");
                         $('#main-box').load(addressValue, function(){
                              MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
                         }
                     
                 );

              });
              
          });
          
      </script>
      
      
    <div class="container">
        <h1><a href="index.jsp">EQ PRO</a></h1>
      <nav id="topNavigation">
        <ul>
        	<li><a href="principalNav/IndexContent.jsp" id="indexjsp">Inicio</a></li>
          <li><a href="principalNav/about.jsp" id="aboutjsp"  >Acerca</a></li>
          <li><a href="principalNav/tutorials.jsp" id="tutorialjsp">Tutorial</a></li>
          <li><a href="principalNav/downloads.jsp" id="downloadsjsp">Descargas</a></li>
          <li><a href="principalNav/contacts.jsp" id="contactsjsp">Contacto</a></li>
          <li><a href="principalNav/sitemap.jsp" id="sitemapjsp">Índice</a></li>
        </ul>
      </nav>
    </div>
</header>

