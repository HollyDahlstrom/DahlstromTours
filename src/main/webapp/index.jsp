<%-- 
    File: index.jsp
    Author: Holly Dahlstrom
    Created: 11/18/2025
    Description: Homepage for How Swede Guided Tours. Displays the hero section,
                 promotional messaging, and a carousel of featured tours to 
                 encourage users to explore available travel packages.
--%>

<%@ include file="header.jsp" %>

<!-- Hero Section -->
<div class="hero text-center p-5 bg-light">
    <h1 style="color: #022c6f;">Explore the World with How Swede Guided Tours</h1>
    <p class="lead">Book your next adventure to Sweden, Germany, Norway, and more!</p>
    <a href="all-tours" class="btn btn-primary btn-lg">View Tours</a>
</div>

<!-- Popular Tours Section -->
<div class="container my-5">
    <h2 class="text-center mb-4">Popular Tours</h2>
<div id="popularToursCarousel" class="carousel slide w-100" data-bs-ride="carousel">
  <!-- Indicators/dots -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#popularToursCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#popularToursCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#popularToursCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>

  <!-- Carousel slides -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="images/sweden.jpg" class="d-block w-100" alt="Sweden Adventure" style="height: 400px; object-fit: cover;">
      <div class="carousel-caption" style="background-color: rgba(0,0,0,0.6); padding: 1rem; border-radius: 0.5rem;">
        <h5>Sweden Adventure</h5>
        <p>Countries: Sweden | Explore Stockholm, Gothenburg, and Lapland on this 10-day journey.</p>
      </div>
    </div>

    <div class="carousel-item">
      <img src="images/germany.jpg" class="d-block w-100" alt="Germany Explorer" style="height: 400px; object-fit: cover;">
      <div class="carousel-caption" style="background-color: rgba(0,0,0,0.6); padding: 1rem; border-radius: 0.5rem;">
        <h5>Germany Explorer</h5>
        <p>Countries: Germany | Discover Berlin, Munich, and the Bavarian countryside on this 8-day tour.</p>
      </div>
    </div>

    <div class="carousel-item">
      <img src="images/norway.jpg" class="d-block w-100" alt="Norway Fjord Adventure" style="height: 400px; object-fit: cover;">
      <div class="carousel-caption" style="background-color: rgba(0,0,0,0.6); padding: 1rem; border-radius: 0.5rem;">
        <h5>Norway Fjord Adventure</h5>
        <p>Countries: Norway | Cruise majestic fjords, explore Oslo and Bergen on this 9-day adventure.</p>
      </div>
    </div>
  </div>

  <!-- Controls -->
  <button class="carousel-control-prev" type="button" data-bs-target="#popularToursCarousel" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#popularToursCarousel" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</div>
<%@ include file="footer.jsp" %>