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
import axios from 'axios';
import React, { useEffect } from 'react';
import { Controller, FormProvider, useForm } from 'react-hook-form';
import { useLocation, useNavigate } from 'react-router';

const drawerWidth = 240;

export const Category = () => {
  const location = useLocation();
  const methods = useForm<any>({
    mode: 'onBlur',
    shouldFocusError: false,
  });

  const { control, getValues, setValue } = methods;
  const navigate = useNavigate();
  const id = location.state?.data || null;

  useEffect(() => {
    if (id != null) {
      fetchData();
    }
  }, []);

  const fetchData = async () => {
    try {
      const res = await axios.get(`/api/category/getCategory/${id}`);

      const data = res.data;
      methods.reset({
        name: data.name,
        description: data.description,
      });
    } catch (error) {
      console.error('Error fetching: ', error);
    }
  };

  const createCategory = async () => {
    try {
      console.log('Values: ', getValues());
      const res = await axios.post('/api/category/createCategory', JSON.stringify(getValues()), {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      navigate('/categories');
    } catch (error) {
      console.error('Error submitting create ', error);
    }
  };

  const updateCategory = async () => {
    try {
      const res = await axios.put(`/api/category/updateCategory/${id}`, JSON.stringify(getValues()), {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      navigate('/categories');
    } catch (error) {
      console.error('Error submitting update ', error);
    }
  };

  const onSubmit = () => {
    if (id == null) createCategory();
    else updateCategory();
  };

  return (
    <>
      <FormProvider {...methods}>
        <form
          onSubmit={e => {
            e.preventDefault();
            methods.handleSubmit(onSubmit, err => {
              console.error(JSON.stringify(err));
            })();
          }}
        >
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
                <Grid item xs={12} sx={{ marginBottom: '20px' }}>
                  <Controller
                    name={'name'}
                    control={control}
                    defaultValue=""
                    render={({ field }) => {
                      return <TextField {...field} label={'Name'} color="info" />;
                    }}
                  />
                </Grid>
                <Grid item xs={12} sx={{ marginBottom: '20px' }}>
                  <Controller
                    name={'description'}
                    control={control}
                    defaultValue=""
                    render={({ field }) => {
                      return <TextField {...field} label={'Description'} color="info" />;
                    }}
                  />
                </Grid>
                <Grid item xs={12} sx={{ marginBottom: '20px' }}>
                  <Button variant="contained" type="submit">
                    Submit
                  </Button>
                </Grid>
              </Grid>
            </Paper>
          </Box>
        </form>
      </FormProvider>
    </>
  );
};
export default Category;
