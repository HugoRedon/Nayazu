<%-- 
    Document   : ComponentTutorial
    Created on : 11-mar-2013, 23:46:38
    Author     : Chilpayate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<article>
            	<h2>Creación de <span> componentes puros</span></h2>
    
Un componente puro tiene las siguientes propiedades:

<table id="compProp">
    <tr>
        <th>Propiedad</th><th>Unidades</th>
    </tr>
    
    <tr><td>Peso Molecular</td><td>\( \frac{kg}{kmol}\)</td></tr>
    <tr><td>Temperatura Crítica</td><td>\( K\)</td></tr>
      <tr><td>Presión Crítica</td><td>\( P_a\)</td></tr>
        <tr><td>Volumen Crítico</td><td>\( \frac{m^3}{kmol}\)</td></tr>
        <tr><td>Factor de compresibilidad Crítico</td><td>\( Adimensional\)</td></tr>
       
        <tr><td>Punto de fusión</td><td>\( K\)</td></tr>
        <tr><td>Temperatura del punto triple</td><td>\( K\)</td></tr>
        <tr><td>Presión del punto triple</td><td>\( P_a\)</td></tr>
        <tr><td>Punto normal de ebullición</td><td>\( K\)</td></tr>
        <tr><td>Volumen molar del líquido</td><td>\( \frac{m^3}{kmol}\)</td></tr>
        
        <tr><td>Entalpía de formación del gas Ideal</td><td>\( \frac{J}{kmol}\)</td></tr>
        <tr><td>Energía de Gibbs de formación del gas Ideal</td><td>\( \frac{J}{kmol}\)</td></tr>
        <tr><td>Entropía absoluta del gas Ideal</td><td>\( \frac{J}{kmol K}\)</td></tr>
        <tr><td>Entalpía de formación en estado estandar</td><td>\( \frac{J}{kmol}\)</td></tr>
        <tr><td>Energía de Gibbs de formación en estado estandar</td><td>\( \frac{J}{kmol}\)</td></tr>
        
        <tr><td>Entropía absoluta en estado estandar</td><td>\( \frac{J}{kmol K}\)</td></tr>
        <tr><td>Entalpía de fusión en el punto de fusión</td><td>\( \frac{J}{kmol}\)</td></tr>
        <tr><td>Entalpía neta de combustión en estado estandar</td><td>\( \frac{J}{kmol}\)</td></tr>
        <tr><td>Factor acéntrico</td><td>\( Adimensional\)</td></tr>
         <tr><td>Radio de giro</td><td>\( m\)</td></tr>
         
          <tr><td>Parámetro de solubilidad</td><td>\(    \sqrt{  \left({   \frac{J}{m^3}  }\right)  }   \)</td></tr>
           <tr><td>Momento dipolar</td><td>\( (C) (m) \)</td></tr>
          <tr><td>Volumen de van der Waals</td><td>\( \frac{m^3}{kmol}\)</td></tr>
          <tr><td>Área de van der Waals</td><td>\( \frac{m^2}{kmol}\)</td></tr>
            <tr><td>Índice de refracción</td><td>\(Adimensional\)</td></tr>
            
            <tr><td>Punto de inflamación</td><td>\(K\)</td></tr>
            <tr><td>Límite inferior de inflamabilidad</td><td>\(  \% en \, volumen  \, en  \,  el  \, aire\)</td></tr>
            <tr><td>Límite superior de inflamabilidad</td><td>\(  \% en \, volumen  \, en  \,  el  \, aire\)</td></tr>
            <tr><td>Temperatura límite inferior de inflamabilidad</td><td>\(  K\)</td></tr>
             <tr><td>Temperatura límite superior de inflamabilidad</td><td>\(  K\)</td></tr>
             
               <tr><td>Temperatura de Ignición</td><td>\(  K\)</td></tr>
             <tr><td>Paracoro</td><td>\(  Adimensional\)</td></tr>
             <tr><td>Entalpía de sublimación</td><td>\(  \frac{J}{kmol}\)</td></tr>
             <tr><td>Constante dieléctrica</td><td>\(  Adimensional\)</td></tr>
             <tr><td>Parámetro kappa para la ecuación de estado <a href="#">PRSV</a></td><td>\(  Adimensional\)</td></tr>
            
</table>
podemos crear componentes de dos formas: 1) empleando el método constructor y definiendo cada una de las propiedades, ó 
2) utilizar la base de datos incorporada (recomendado):
<br/>
<h3>1) Creación de un componente con el método constructor:</h3>
<br/>
 <div class="containsCode">
        <pre class="brush: java; toolbar: false; title:''; " >
               import termo.component.Component;
        </pre>
         <pre class="brush: java; toolbar: false; title:''; " >
                  Component methanol = new Component();
                    methanol.setName("Methanol");
                    methanol.setCasNumber("67-56-1");

                    methanol.setCriticalPressure(8.08400e6);
                    methanol.setCriticalTemperature(512.5);
                    methanol.setAcentricFactor(0.565831);

                    methanol.setPrsvKappa(0.39379);
         </pre>
 </div>
Las propiedades que no se establezcan, por default tienen un valor de 0.<br/>
<h3>2) Creación de un componente utilizando la base de datos incorporada:</h3>
<div class="containsCode">
        <pre class="brush: java; toolbar: false; title:''; " >
               import termo.component.Component;
               import termo.dipprComponentLoader.ComponentManager;
        </pre>
         <pre class="brush: java; toolbar: false; title:''; " >
                   ComponentManager manager = new ComponentManager();
                   Component water = manager.getComponentByName("water");
                   Component methanol = manager.getComponentByCasNumber("67-56-1");
         </pre>
 </div>
Nótese que podemos obtener componentes a través del nombre o el numero de registro CAS por sus siglas en inglés (Chemical Abstracts Service).

Finalmente podemos acceder a sus propiedades con los metodos "getXxx();"

  <div class="containsCode">
         <pre class="brush: java; toolbar: false; title:''; " >
                double pc = methanol.getCriticalPressure();
                double acentricFactor = water.getAcentricFactor();;
                ...
         </pre>
 </div>
<script type="text/javascript">
                        SyntaxHighlighter.all();
                        SyntaxHighlighter.highlight();  
      </script>
       </article>
