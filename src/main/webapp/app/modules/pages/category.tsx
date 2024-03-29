import {
  AppBar,
  Box,
  Button,
  FormControl,
  Grid,
  InputLabel,
  Link,
  MenuItem,
  Paper,
  Select,
  TextField,
  Toolbar,
  Typography,
} from '@mui/material';
import React from 'react';

const drawerWidth = 240;

export const Category = () => {
  return (
    <>
      <Box component="main" sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}>
        {/* <Toolbar /> */}
        <AppBar position="fixed" sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}>
          <Toolbar>
            {/* <Typography variant="h6" noWrap component="div">
              Dashboard
            </Typography> */}
          </Toolbar>
        </AppBar>
        <Grid container alignItems={'center'}>
          <Grid item sx={{ marginRight: '20px' }}>
            <Link underline="none" href="/inventory">
              <Typography variant="body1" style={{ marginBottom: '10px' }}>
                {'< Back'}
              </Typography>
            </Link>
          </Grid>
          <Grid item>
            <Typography variant="h4" style={{ marginBottom: '10px' }}>
              Category
            </Typography>
          </Grid>
        </Grid>

        <Paper sx={{ width: '60%', mb: 2, marginLeft: '80px', padding: '20px 30px' }}>
          <Grid container>
            <Grid xs={12} sx={{ marginBottom: '20px' }}>
              <TextField label={'Name'} />
            </Grid>
            <Grid xs={12} sx={{ marginBottom: '20px' }}>
              <TextField label={'Description'} multiline rows={4} />
            </Grid>
            <Grid xs={12} sx={{ marginBottom: '20px' }}>
              <Button variant="contained">Submit</Button>
            </Grid>
          </Grid>
        </Paper>
      </Box>
    </>
  );
};
export default Category;
