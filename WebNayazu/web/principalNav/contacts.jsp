<%-- 
    Document   : contacts
    Created on : 07-mar-2013, 0:27:45
    Author     : Chilpayate
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <div class="container">
      <div class="inside">
        <div class="wrapper">
        	<!-- aside -->
          <aside>
            <h2>Comunícate <span> con Nosotros</span></h2>
            <!-- .contacts -->
            <ul class="contacts">
            	
              <li><strong>País:</strong>Estados Unidos Mexicanos</li>
              <li><strong>Ciudad:</strong>Distrito Federal</li>
              <li><strong>Teléfono:</strong>+354 5635600</li>
              <li><strong>Fax:</strong>+354 5635620</li>
              <li><strong>Correo electrónico:</strong><a href="#">contactoEQPRO@mail.com</a></li>
            </ul>
            <!-- /.contacts -->
            <h3>Horario de atención:</h3>
            Lunes a viernes de 10:00am - 8pm
          </aside>
          <!-- content -->
          <section id="content">
            <article>
            	<h2>Escríbenos <span>tus ideas</span></h2>
              <form id="contacts-form" action="" method="post">
                <fieldset>
                  <div class="field">
                    <label>Nombre:</label>
                    <input type="text" value=""/>
                  </div>
                  <div class="field">
                    <label>Correo electrónico:</label>
                    <input type="email" value=""/>
                  </div>
                  <div class="field">
                    <label>Asunto:</label>
                    <input type="url" value=""/>
                  </div>
                  <div class="field">
                    <label>Tu mensaje:</label>
                    <textarea></textarea>
                  </div>
                  <div><a href="#" onclick="document.getElementById('contacts-form').submit()">Envía tu mensaje!</a></div>
                </fieldset>
              </form>
            </article> 
          </section>
        </div>
      </div>
    </div>


