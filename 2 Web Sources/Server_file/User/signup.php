<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include '../DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $username = $_POST['username'];
 $user_password = $_POST['user_password'];
 $email = $_POST['email'];
 $phoneno = $_POST['phoneno'];

 $CheckSQL = "SELECT * FROM useraccount WHERE username ='$username'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'Username Already Exist';

 }
else{ 
$Sql_Query = "INSERT INTO useraccount(username,user_password,email,phoneno) values ('$username','$user_password','$email','$phoneno')";
$Sql_Query_license_data = "INSERT INTO license_data (username,state,dlno,fullname,sdwhof,dob,age,dateofissue,validtill,bloodgroup,permanentaddress,currentaddress) values ('$username','Data Not Found','Data Not Found','Data Not Found','Data Not Found','Data Not Found','Data Not Found','Data Not Found','Data Not Found','Data Not Found','Data Not Found','Data Not Found')";
$Sql_Query_license_type = "INSERT INTO license_type(username,mcwg,mgv,lmv,hmv,hgmv,hpmv) values ('$username','NA','NA','NA','NA','NA','NA')";
$Sql_Query_addFine = "Insert into userfine(username,policename,reason,fineamount) values ('$username','NA','NA','0')";

 if(mysqli_query($con,$Sql_Query_addFine) && mysqli_query($con,$Sql_Query) && mysqli_query($con,$Sql_Query_license_type)  && mysqli_query($con,$Sql_Query_license_data))
 {
    echo 'Signed Up Sucessfully';
   }
   else
   {
    echo 'Something went wrong';
    }
    }
   }
   // mysqli_close($con);
   ?>