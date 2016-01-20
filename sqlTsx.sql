drop table Tsx;

truncate table Tsx;

create table Tsx (
	ticker varchar(30),
	date_ date,
	open_ smallmoney,
	high smallmoney,
	low smallmoney,
	close_ smallmoney,
	vol int
);


bulk insert Tsx
from 'C:\Users\Ypershin\Google Drive\TSX\TSX_2016\TSX_2016.csv'
with (
	firstrow=2,
	fieldterminator = ',',
	rowterminator = '\n',
	errorfile = 'C:\Users\Ypershin\Google Drive\TSX\TSX_2016\error.csv',
	tablock
);


create nonclustered index iTsx on Tsx(ticker);
create nonclustered index i2Tsx on Tsx(ticker,date_);


select distinct ticker from Tsx
where ticker like ('ALA%')


select top 100 * from Tsx
where ticker='ALA' and date_ between '01/01/2016' and '01/15/2016'
order by date_ desc


