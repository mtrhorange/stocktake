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
import React, { useEffect } from 'react';

import { DataGrid, GridColDef, GridValueGetterParams } from '@mui/x-data-grid';
import { Controller, FormProvider, useForm } from 'react-hook-form';
import axios from 'axios';
import { toast } from 'react-toastify';
import { useLocation, useNavigate } from 'react-router';
const drawerWidth = 240;

export const Product = () => {
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
      const res = await axios.get(`/api/products/getProduct/${id}`);

      const data = res.data;
      methods.reset({
        name: data.name,
        description: data.description,
        price: data.price,
      });
    } catch (error) {
      console.error('Error fetching: ', error);
    }
  };

  const createProduct = async () => {
    try {
      const res = await axios.post('/api/products/createProduct', JSON.stringify(getValues()), {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      toast.success('Product successfully created');

      navigate('/inventory');
    } catch (error) {
      toast.error('Product create unsuccessfully');
      console.error('Error submitting create ', error);
    }
  };

  const updateProduct = async () => {
    try {
      const res = await axios.put(`/api/products/updateProduct/${id}`, JSON.stringify(getValues()), {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      toast.success('Product successfully updated');

      navigate('/inventory');
    } catch (error) {
      toast.error('Product updated unsuccessfully');
      console.error('Error submitting update ', error);
    }
  };

  const onSubmit = () => {
    setValue('quantity', 0);
    if (id == null) createProduct();
    else updateProduct();
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
                  Product
                </Typography>
              </Grid>
            </Grid>

            <Paper sx={{ width: '60%', mb: 2, marginLeft: '80px', padding: '20px 30px' }}>
              <Grid container>
                <Grid xs={12} sx={{ marginBottom: '20px' }}>
                  <Controller
                    name={'name'}
                    control={control}
                    defaultValue=""
                    render={({ field }) => {
                      return <TextField {...field} label={'Name'} color="info" />;
                    }}
                  />
                </Grid>
                <Grid xs={12} sx={{ marginBottom: '20px' }}>
                  <Controller
                    name={'description'}
                    control={control}
                    defaultValue=""
                    render={({ field }) => {
                      return <TextField {...field} label={'Description'} color="info" />;
                    }}
                  />
                </Grid>
                <Grid xs={12} sx={{ marginBottom: '20px' }}>
                  <Controller
                    name={'price'}
                    control={control}
                    defaultValue=""
                    render={({ field }) => {
                      return <TextField {...field} label={'Price'} color="info" />;
                    }}
                  />
                </Grid>
                <Grid xs={12} sx={{ marginBottom: '20px' }}>
                  <FormControl style={{ width: '20%' }}>
                    <InputLabel>Category</InputLabel>
                    <Select label="Category" defaultValue={'default'}>
                      <MenuItem value="default">Default</MenuItem>
                      <MenuItem value="food">Food</MenuItem>
                      <MenuItem value="drinks">Drinks</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>
                <Grid xs={12} sx={{ marginBottom: '20px' }}>
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
export default Product;
