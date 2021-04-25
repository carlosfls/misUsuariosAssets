CREATE VIEW MCVS_HABANA_USUARIOS_ASSETS AS
select distinct Id_Comercial,Desc_Comercial,CI,Id_User,AssetsUser
 FROM dbo.Empleado where Id_User!=''