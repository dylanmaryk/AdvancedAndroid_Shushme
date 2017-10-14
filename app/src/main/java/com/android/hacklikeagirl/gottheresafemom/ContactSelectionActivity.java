package com.android.hacklikeagirl.gottheresafemom;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.onegravity.contactpicker.contact.Contact;
import com.onegravity.contactpicker.contact.ContactDescription;
import com.onegravity.contactpicker.contact.ContactSortOrder;
import com.onegravity.contactpicker.core.ContactPickerActivity;
import com.onegravity.contactpicker.picture.ContactPictureType;
import java.util.ArrayList;
import java.util.List;

public class ContactSelectionActivity extends Activity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
      if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
      }
    } else {
      Intent intent = new Intent(this, ContactPickerActivity.class)
          .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE, ContactPictureType.ROUND.name())
          .putExtra(ContactPickerActivity.EXTRA_SHOW_CHECK_ALL, true)
          .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION, ContactDescription.ADDRESS.name())
          .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
          .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER, ContactSortOrder.AUTOMATIC.name());
      startActivityForResult(intent, 0);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    switch (requestCode) {
      case 0: {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Intent intent = new Intent(this, ContactSelectionActivity.class)
              .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE, ContactPictureType.ROUND.name())
              .putExtra(ContactPickerActivity.EXTRA_SHOW_CHECK_ALL, true)
              .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION, ContactDescription.ADDRESS.name())
              .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
              .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER, ContactSortOrder.AUTOMATIC.name());
          startActivityForResult(intent, 0);
        }
        return;
      }
    }
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null && data.hasExtra(com.onegravity.contactpicker.core.ContactPickerActivity.RESULT_CONTACT_DATA)) {
      SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPref.edit();
      List<Contact> contacts = (List<Contact>) data.getSerializableExtra(com.onegravity.contactpicker.core.ContactPickerActivity.RESULT_CONTACT_DATA);
      List numbers = new ArrayList();
      for (Contact contact : contacts) {
        numbers.add(contact.getPhone(0));
      }
      editor.putString("numbers", TextUtils.join(",", numbers));
      editor.apply();
      finish();
    }
  }
}
