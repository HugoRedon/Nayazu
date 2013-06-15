<%-- 
    Document   : tutorialsAside
    Created on : 08-mar-2013, 1:31:20
    Author     : Chilpayate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<aside class="thin">
            <h2>Temas <span>!</span></h2>
            
    <script type="text/javascript">
          $(document).ready(function(){
              $('#sideNavigation a').click(function (event){          
                       event.preventDefault();
                        $('#sideNavigation a').removeClass('selected');
                         $(this).addClass('selected');                
                         var addressValue = $(this).attr("href");
                         $('#content').load(addressValue,function(){
                             MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
                         });

              });
              
          });
          
      </script>
            
            <nav id="sideNavigation">
            <ul class="tutorialIndex">
                <li>
                    <figure>Componentes puros</figure>
                    <ul>
                        <li><h3><a  href="tutorials/ComponentTutorial.jsp" id="idealGas"  >Creación de componentes</a></h3></li>
                    </ul>
                </li>
                <li>
              	<figure>Ecuaciones de estado</figure>
                        <ul>
                            <li><h3><a  href="tutorials/IdealGasTest.jsp" id="idealGas"  >Gas Ideal</a></h3></li>
                            <li><h3><a  href="tutorials/CubicEquationTutorialJSP.jsp" id ="pr"  >Cúbica</a></h3>
                                <ul>
                                    <li><h3><a  href="tutorials/AlphaTutorial.jsp" id ="vdw"  >\( \alpha \left( T\right) \)  para el cálculo de \(a\)</a></h3></li>
                                </ul>
                            </li>

                        </ul>
                
                
                   </li>
              <li>
              	<figure>Modelos de actividad</figure>
                       <ul>
                    <li><h3><a  href="tutorialsIdealGas.jsp" id="idealGas"  >Wilson</a></h3></li>
                    <li><h3><a  href="tutorialsPengRobinson.jsp" id ="pr"  >NRTL</a></h3></li>
                    <li><h3><a  href="tutorialsVanderWaals.jsp" id ="vdw"  >UNIQUAC</a></h3></li>
                    
                </ul>
                     
                
              </li>
              <li>
              	<figure>Cálculos de equilibrio</figure>
                <ul>
                    <li><h3><a  href="tutorialsIdealGas.jsp" id="idealGas"  >Temperatura de Burbuja</a></h3></li>
                    <li><h3><a  href="tutorialsPengRobinson.jsp" id ="pr"  >Presión de rocío</a></h3></li>
                    <li><h3><a  href="tutorialsVanderWaals.jsp" id ="vdw"  >Flash</a></h3></li>
                    
                </ul>
              </li>
              <li>
              	<figure>Propiedades de substancias</figure>
               <ul>
                    <li><h3><a  href="tutorialsIdealGas.jsp" id="idealGas"  >Entalpía</a></h3></li>
                    <li><h3><a  href="tutorialsPengRobinson.jsp" id ="pr"  >Entropía</a></h3></li>
         
                </ul>
              </li>
          
            </ul>
                </nav>
            
          </aside>
