<%-- 
    Document   : AlphaTutorial
    Created on : 11-mar-2013, 14:05:38
    Author     : Chilpayate
--%>

<%@page import="termo.eos.IdealGas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

       <article>
            	<h2>Expresiones para  <span> \( \alpha \left(   T \right) \)</span></h2>
    Soave - Peng and Robinson: 
   \(  \alpha_i    =    \left[{   1 +   m_i \left({    1-     \sqrt{  \frac{T}{T_{ci}}   } }\right)   }\right] ^2  \)<br/> 
   Mathias - Stryjek and Vera 
          \(  \alpha_i    =    \left[{
                1 +   m_i \left({    1-     \sqrt{  \frac{T}{T_{ci}}   } }\right)  + q_i  \left({ 1 -  \frac{T}{T_{ci}}}\right) 
                \left({ 0.7 -  \frac{T}{T_{ci}}}\right) 
          }\right] ^2  \)<br/> 

donde:
<br/>
<span class="indent">
    \(R\) : 8314.472 \(\frac{m^3 P_a}{K kmol}\) constante universal de los gases ideales.<br/>
    \(T\) : temperatura absoluta en Kelvin.<br/>
    \(V\) : Volumen molar en \(\frac{m^3}{kmol} \).<br/>
</span>
 Para realizar un calculo de presión:<br/>
 <span class="indent">
    temperatura =\( 300 K \)<br/>
    volume molar = \(  13 \frac{ m^3 }{kmol} \)<br/>
 </span>
 segun la ecuación de estado:<br/>
 
 \begin{aligned}
    P = \frac{8314.472  \frac{m^3 P_a}{K kmol}   * 300 K  }{13  \frac{m^3}{kmol}  } = 191872.43077  P_a
\end{aligned}
 
  <div class="containsCode">
        <pre class="brush: java; toolbar: false; title:''; " >
                import termo.eos.IdealGas;
        </pre>
         <pre class="brush: java; toolbar: false; title:''; " >
                IdealGas idealGasInstance = new IdealGas();
                double temperature = 300;
                double volume = 13;

                double result = idealGasInstance.getPressure(temperature, volume);
         </pre>
 </div>

Podemos ver el resultado que se obtiene del código: <span class ="result">\( <%= new IdealGas().getPressure(300, 13) %>   P_a \)</span>
<script type="text/javascript">
                        SyntaxHighlighter.all();
                        SyntaxHighlighter.highlight();  
</script>
       </article>
