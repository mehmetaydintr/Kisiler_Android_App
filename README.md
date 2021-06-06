# Kisiler Android App

<img src="https://user-images.githubusercontent.com/37263322/120923287-6aadad00-c6d6-11eb-8768-1fb7ca573553.gif" width="300">

## İçerik

1. [Kullanılan Teknolojiler](https://github.com/mehmetaydintr/Bayrak_Quiz_App/blob/main/README.md#kullan%C4%B1lan-teknolojiler)
2. [Proje Tanımı](https://github.com/mehmetaydintr/Bayrak_Quiz_App/blob/main/README.md#proje-tan%C4%B1m%C4%B1)
3. [Kod Tanımı](https://github.com/mehmetaydintr/Bayrak_Quiz_App/blob/main/README.md#kod-tan%C4%B1m%C4%B1)
4. [Örnek Ekran Görüntüleri](https://github.com/mehmetaydintr/Bayrak_Quiz_App/blob/main/README.md#%C3%B6rnek-ekran-g%C3%B6r%C3%BCnt%C3%BCleri)


## Kullanılan Teknolojiler

+ Android Studio

![Image of Android Studio](https://www.xda-developers.com/files/2017/04/android-studio-logo.png)

+ Java

![Image of Java](https://yazilimamelesi.files.wordpress.com/2013/03/java_logo.jpg)

+ SQLite

![Image of SQLite](https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/SQLite370.svg/1280px-SQLite370.svg.png)


## Proje Tanımı

SQLite veritabanı kullanarak **Android** tabanlı geliştirilmiş basit bir rehber örnek projedir.

**_SQLite_** kurulumu ve kullanımı oldukça kolay olan bir veritabanı kütüphanesidir. Kullanmak için kurulum yapmanız gerekmez. Diğer birçok veritabanı yönetim sisteminin aksine, SQLite bir istemci-sunucu veritabanı motoru değildir. Aksine, son programın içine yerleştirilmiştir. Veritabanına erişmek için sunucuya istek göndermek ve sonuç almak için ara işlem iletişimi kurmak gerekmez. Bu program doğrudan diskteki veritabanı dosyalarını okur ve yazar. Web tarayıcıları gibi uygulama yazılımlarında yerel / istemci depolaması için yerleşik bir veritabanı yazılımı olarak popüler bir seçimdir. Günümüzde birçok yaygın tarayıcı, işletim sistemi ve gömülü sistem (cep telefonları gibi) tarafından kullanıldığı için tartışmasız en yaygın kullanılan veritabanı motorudur.

## Kod Tanımı

+ İlk olarak tasarım ile başlayalım. 

Öncelikle `Toolbar`ımıza menü ekleyeceğimiz için mevcut bulunan toolbarı kaldırmamız gerekmektedir. Bunun için proje dizinindeki `res\values\themes` klasörü içerisinde bulunan hem aydınlık hem de karanlık tema için 2 adet `themes.xml` dosyasını açıyoruz.

![1](https://user-images.githubusercontent.com/37263322/120920744-06382100-c6c9-11eb-82f4-b6d0cf3f2dc3.png)

Daha sonrasında bu 2 dosya içerisine bu 2 kod satırını ekliyoruz.

```
<item name="windowNoTitle">true</item>
<item name="windowActionBar">false</item>
```

![2](https://user-images.githubusercontent.com/37263322/120920831-79da2e00-c6c9-11eb-870e-d33c03147fb9.png)

Şimdi ana ekranımızın tasarımına geçebiliriz. Ana ekranımıza bir adet `Toolbar`, bir adet kisileri listeleyebilmek için `RecyclerView`, bir adet `FloatingActionButton` ekliyoruz.

![3](https://user-images.githubusercontent.com/37263322/120920942-fec54780-c6c9-11eb-8518-ba2de0276fb7.png)

Recycler View, klasik listelere ek olarak özelleştirilmiş liste imkanı sunmaktadır. Bu yüzden Recycler View kullandık. Şimdi Recycler View içerisinde gösterilecek her item için özel bir tasarım yapacağız. Projemizin dizininde `res\layout` klasörüne **card_tasarim** isimli yeni bir `Layout Resource File` oluşturuyoruz. `card_tasarim`'ımızın içerisine bir adet `CardView`, Card View'ımızın içerisine bir adet `ConstraintLayout`, Layout'umuzun içerisine ortalayarak içerikleri yazdırabileceğimiz bir `TextView` ve her item'e özel menü yapabilmek için de sağ tarafına bir adet `ImageView` ekliyoruz. En dışarıdaki Layoutumuzun `layout_height` özelliğini **50dp** olarak ayarlıyoruz.

![4](https://user-images.githubusercontent.com/37263322/120921322-d3435c80-c6cb-11eb-8e70-86dc013f3a28.png)

Yeni kişi eklemek ve mevcut kişiyi güncellemek için **Alert Dialog** kullanacağız. Bunun için de `res\layout` klasörüne **alert_tasarim** isimli yeni bir `Layout Resource File` oluşturuyoruz. `alert_tasarim`'ımızın içerisine 2 adet `EditText` ekliyoruz.

Şimdi de **menü** tasarımlarımıza geçelim. Toolbar'da kullanacağımız menü için `res\menu` klasörüne **toolbar_menu** isimli yeni bir `Menu Resource File` oluşturuyoruz ve içerisine `Search Item` ekliyoruz. Sürekli ikon olarak görünmesini istediğimiz için `showAsAction` özelliğini **always|collapseActionView** olarak ayarlıyoruz.

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/action_search"
        android:icon="@drawable/ic_baseline_search_24"
        android:title="Ara"
        app:actionViewClass="androidx.appcompat.widget.SearchView"
        app:showAsAction="always|collapseActionView" />
</menu>
```

Şimdi de **RecyclerView** içerisindeki itemlere ait olacak menü tasarımını yapalım. Aynı klasör içerisine **card_menu** isimli yeni bir `Menu Resource File` oluşturuyoruz ve içerisine 2 adet `Menu Item` ekliyoruz. Sürekli kapalı bir menü olarak görünmesini istediğimiz için `showAsAction` özelliğini **never** olarak ayarlıyoruz.


```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/action_delete"
        android:title="Sil"
        app:showAsAction="never" />
    <item
        android:id="@+id/action_edit"
        android:title="Güncelle"
        app:showAsAction="never" />
</menu>
```

+ Şimdi kodlama kısmına geçebiliriz.

Öncelikle SQLite veritabanımız içerisindeki tablolarımızı kurmak için `Veritabani` sınıfı oluşturuyoruz. `onCreate` metodu ile ihtiyacımız olan tabloları oluşturacağız.

```
public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(@Nullable Context context) {
        super(context, "kisiler.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS kisiler (" +
                "kisi_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kisi_ad TEXT," +
                "kisi_tel TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS kisiler");
        onCreate(sqLiteDatabase);

    }
}
```

Daha sonra veritabanı ile yapacağımız **CRUD** işlemleri için bir servis yazacağız. `KisilerDao` sınıfı oluşturuyoruz. Bu sınıf içerisinde veri çekmek, güncellemek, silmek ve kaydetmek için metotlarımız olacak. Metotlar içerisindeki `Cursor` sql kodlarımızı işlememizi sağlıyor.

```
public class KisilerDao {

    public ArrayList<Kisiler> tumKisiler(Veritabani vt) {
        ArrayList<Kisiler> kisilers = new ArrayList<>();

        SQLiteDatabase database = vt.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kisiler", null);

        while (c.moveToNext()) {
            kisilers.add(new Kisiler(c.getInt(c.getColumnIndex("kisi_id")),
                    c.getString(c.getColumnIndex("kisi_ad")),
                    c.getString(c.getColumnIndex("kisi_tel"))));
        }

        database.close(); 
        return kisilers;
    }

    public ArrayList<Kisiler> kisiAra(Veritabani vt, String isim) {
        ArrayList<Kisiler> kisilers = new ArrayList<>();

        SQLiteDatabase database = vt.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kisiler WHERE kisi_ad like '%" + isim + "%'", null);

        while (c.moveToNext()) {
            kisilers.add(new Kisiler(c.getInt(c.getColumnIndex("kisi_id")),
                    c.getString(c.getColumnIndex("kisi_ad")),
                    c.getString(c.getColumnIndex("kisi_tel"))));
        }
        database.close();
        return kisilers;
    }

    public void kisiSil(Veritabani vt, int id){

        SQLiteDatabase database = vt.getWritableDatabase();
        database.delete("kisiler","kisi_id=?", new String[]{String.valueOf(id)});
        database.close();
    }

    public void kisiEkle(Veritabani vt, String ad, String tel) {

        SQLiteDatabase database = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kisi_ad", ad);
        values.put("kisi_tel", tel);

        database.insertOrThrow("kisiler",null, values);
        database.close();
    }

    public void kisiGuncelle(Veritabani vt, int id, String ad, String tel) {

        SQLiteDatabase database = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kisi_ad", ad);
        values.put("kisi_tel", tel);

        database.update("kisiler", values,"kisi_id=?", new String[]{String.valueOf(id)});
        database.close();
    }
}
```

Şimdi **RecyclerView** için gerekli olan adapterımızı yazacağız. `KisilerAdapter` sınıfı oluşturuyoruz. Bu sınıfımız veritabanından gelen verileri oluşturmuş olduğumuz `card_tasarim` içerisindeki **TextView** içerisine yazar ve eklemiş olduğumuz **ImageView**'a tıklanıldığı zaman `card_menu` menümüzü popup olarak göstereceğiz.

```
public class KisilerAdapter extends RecyclerView.Adapter<KisilerAdapter.CardTutucu>{

    private Context context;
    private List<Kisiler> kisilerList;
    private Veritabani veritabani;

    public KisilerAdapter(Context context, List<Kisiler> kisilerList, Veritabani veritabani) {
        this.context = context;
        this.kisilerList = kisilerList;
        this.veritabani = veritabani;
    }

    @NonNull
    @Override
    public CardTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim, parent, false);
        return new CardTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTutucu holder, int position) {
        Kisiler k = kisilerList.get(position);

        holder.textView.setText(k.getKisi_ad() + " - " + k.getKisi_tel());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.imageView);
                popupMenu.getMenuInflater().inflate(R.menu.card_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_delete:
                                Snackbar.make(holder.imageView,"Kişi silinsin mi?", Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        new KisilerDao().kisiSil(veritabani, k.getKisi_id());

                                        kisilerList = new KisilerDao().tumKisiler(veritabani);

                                        notifyDataSetChanged();
                                    }
                                }).show();
                                return true;
                            case R.id.action_edit:
                                alertGoster(k);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kisilerList.size();
    }

    public class CardTutucu extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public CardTutucu(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void alertGoster(Kisiler kisi) {

        LayoutInflater layout = LayoutInflater.from(context);
        View tasarim = layout.inflate(R.layout.alert_tasarim, null);

        EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        editTextAd.setText(kisi.getKisi_ad());
        editTextTel.setText(kisi.getKisi_tel());

        AlertDialog.Builder ad = new AlertDialog.Builder(context);

        ad.setTitle("Kişi Güncelle");
        ad.setView(tasarim);
        ad.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String kisiAd = editTextAd.getText().toString().trim();
                String kisiTel = editTextTel.getText().toString().trim();

                new KisilerDao().kisiGuncelle(veritabani,kisi.getKisi_id(),kisiAd,kisiTel);

                kisilerList = new KisilerDao().tumKisiler(veritabani);

                notifyDataSetChanged();
            }
        });
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.create().show();
    }
}
```

Şimdi ana ekranımızın kodlarına geçelim. İlk olarak ihtiyacımız olan nesnelerin tanımlamalarını yapalım.

```
private Toolbar toolbar;
private RecyclerView rv;
private FloatingActionButton fab;

private ArrayList<Kisiler> kisilers;
private Veritabani veritabani;
private KisilerAdapter adapter;
```

Daha sonra nesnelerin atamalarını yapıp **RecyclerView**'ı aktif hale getirelim.

```
toolbar = findViewById(R.id.toolbar);
rv = findViewById(R.id.rv);
fab = findViewById(R.id.floatingActionButton);

toolbar.setTitle("Kişiler");
setSupportActionBar(toolbar);

veritabani = new Veritabani(this);
kisilers = new KisilerDao().tumKisiler(veritabani);

adapter = new KisilerAdapter(this, kisilers, veritabani);

rv.setHasFixedSize(true);
rv.setLayoutManager(new LinearLayoutManager(this));
rv.setAdapter(adapter);
```

**FloatActionButton**'a tıklanıldığı zaman kişi bilgilerini gireceğimiz **Alert Dialog**'u gösterecek ve alert dialog içerisindeki işlemleri yapacak metodu yazalım.

```
fab.setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View view) {
    alertGoster();
  }
});
```

```
public void alertGoster(){
  LayoutInflater layout = LayoutInflater.from(this);
  View tasarim = layout.inflate(R.layout.alert_tasarim,null);

  EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
  EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

  AlertDialog.Builder ad = new AlertDialog.Builder(this);

  ad.setTitle("Kişi Ekle");
  ad.setView(tasarim);
  ad.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      String kisiAd = editTextAd.getText().toString().trim();
      String kisiTel = editTextTel.getText().toString().trim();

      new KisilerDao().kisiEkle(veritabani,kisiAd,kisiTel);

      kisilers = new KisilerDao().tumKisiler(veritabani);
      adapter = new KisilerAdapter(MainActivity.this, kisilers, veritabani);
      rv.setAdapter(adapter);
    }
  });
  ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {    }
  });

  ad.create().show();
}
```

Toolbar üzerindeki menümüzü ekleyelim ve arama özelliğini hem harf harf arama hem de tüm kelime olarak arama olarak 2 şekilde aktif edelim. Arama özelliğini kullanabilmek için SearchView.OnQueryTextListener interface'ini implement edelim.

```
//Menüyü aktifleştirme
@Override
public boolean onCreateOptionsMenu(Menu menu) {

  getMenuInflater().inflate(R.menu.toolbar_menu, menu);

  MenuItem item = menu.findItem(R.id.action_search);
  SearchView searchView = (SearchView) item.getActionView();

  searchView.setOnQueryTextListener(this);

  return super.onCreateOptionsMenu(menu);
}
```

```
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
```

```
//Kelime olarak arama
@Override
public boolean onQueryTextSubmit(String query) {
  kisilers = new KisilerDao().kisiAra(veritabani,query);
  adapter = new KisilerAdapter(MainActivity.this, kisilers, veritabani);
  rv.setAdapter(adapter);
  return false;
}

//Harf harf arama
@Override
public boolean onQueryTextChange(String newText) {
  kisilers = new KisilerDao().kisiAra(veritabani,newText);
  adapter = new KisilerAdapter(MainActivity.this, kisilers, veritabani);
  rv.setAdapter(adapter);
  return false;
}
```

## Örnek Ekran Görüntüleri

![5](https://user-images.githubusercontent.com/37263322/120923208-fffc7180-c6d5-11eb-8dd7-99f2e1cc3359.png)
![6](https://user-images.githubusercontent.com/37263322/120923210-012d9e80-c6d6-11eb-95aa-e1dc59a67a8e.png)
![7](https://user-images.githubusercontent.com/37263322/120923212-02f76200-c6d6-11eb-95f2-1d02ff0bedde.png)
![8](https://user-images.githubusercontent.com/37263322/120923213-04288f00-c6d6-11eb-912a-90f067b1918a.png)
![9](https://user-images.githubusercontent.com/37263322/120923214-068ae900-c6d6-11eb-85bb-92340ff7f8e3.png)
![10](https://user-images.githubusercontent.com/37263322/120923215-07bc1600-c6d6-11eb-9a80-a7e5b0194eb3.png)
![11](https://user-images.githubusercontent.com/37263322/120923217-08ed4300-c6d6-11eb-8e31-adb10b3f3619.png)

