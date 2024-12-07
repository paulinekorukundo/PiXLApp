import { Box, Typography, Grid, Link, IconButton } from "@mui/material";
import { Facebook, Instagram, Twitter, YouTube } from "@mui/icons-material";

const Footer = () => {
  return (
    <Box
      component="footer"
      sx={{
        position: "sticky",
        bottom: 0,
        backgroundColor: "#f8f9fa",
        padding: "20px 15px",
        borderTop: "1px solid #e7e7e7",
        boxShadow: "0 -2px 5px rgba(0, 0, 0, 0.1)", // Subtle shadow for elevation
      }}
    >
      <Grid container spacing={4} justifyContent="center">
        {/* About Section */}
        <Grid item xs={12} sm={4}>
          <Typography variant="h6" gutterBottom>
            About Us
          </Typography>
          <Typography variant="body2" color="textSecondary">
            Discover, share, and savor amazing recipes with a community of food
            lovers! Join us and explore your culinary passion.
          </Typography>
        </Grid>

        {/* Quick Links */}
        <Grid item xs={12} sm={4}>
          <Typography variant="h6" gutterBottom>
            Quick Links
          </Typography>
          <Link
            to="/"
            color="inherit"
            underline="hover"
            display="block"
            style={{ cursor: "pointer" }}
          >
            Home
          </Link>
          <Link
            to="/recipes"
            color="inherit"
            underline="hover"
            display="block"
            style={{ cursor: "pointer" }}
          >
            Recipes
          </Link>
          <Link href="#" color="inherit" underline="hover" display="block">
            Community
          </Link>
          <Link href="#" color="inherit" underline="hover" display="block">
            Contact Us
          </Link>
        </Grid>

        {/* Follow Us */}
        <Grid item xs={12} sm={4}>
          <Typography variant="h6" gutterBottom>
            Follow Us
          </Typography>
          <Box>
            <IconButton
              href="#"
              target="_blank"
              color="primary"
              sx={{
                transition: "transform 0.3s ease-in-out",
                "&:hover": {
                  transform: "scale(1.2)", // Scale effect on hover
                },
              }}
            >
              <Facebook />
            </IconButton>
            <IconButton
              href="#"
              target="_blank"
              color="secondary"
              sx={{
                transition: "transform 0.3s ease-in-out",
                "&:hover": {
                  transform: "scale(1.2)",
                },
              }}
            >
              <Instagram />
            </IconButton>
            <IconButton
              href="#"
              target="_blank"
              color="primary"
              sx={{
                transition: "transform 0.3s ease-in-out",
                "&:hover": {
                  transform: "scale(1.2)",
                },
              }}
            >
              <Twitter />
            </IconButton>
            <IconButton
              href="#"
              target="_blank"
              color="error"
              sx={{
                transition: "transform 0.3s ease-in-out",
                "&:hover": {
                  transform: "scale(1.2)",
                },
              }}
            >
              <YouTube />
            </IconButton>
          </Box>
        </Grid>
      </Grid>

      <Box sx={{ textAlign: "center", marginTop: "20px" }}>
        <Typography variant="body2" color="textSecondary">
          &copy; {new Date().getFullYear()} PiXl. All rights reserved.
        </Typography>
      </Box>
    </Box>
  );
};

export default Footer;
