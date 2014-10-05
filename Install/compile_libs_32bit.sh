#!/bin/sh
#
#  Copyright 2013 Google Inc. All Rights Reserved.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#      http:# www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

g++ -shared -fPIC -O2\
  cldutil.cc cldutil_shared.cc compact_lang_det.cc compact_lang_det_hint_code.cc \
  compact_lang_det_impl.cc  debug.cc fixunicodevalue.cc \
  generated_entities.cc  generated_language.cc generated_ulscript.cc  \
  getonescriptspan.cc lang_script.cc offsetmap.cc  scoreonescriptspan.cc \
  tote.cc utf8statetable.cc  \
  cld_generated_cjk_uni_prop_80.cc cld2_generated_cjk_compatible.cc  \
  cld_generated_cjk_delta_bi_4.cc generated_distinct_bi_0.cc  \
  cld2_generated_quadchrome0122_2.cc cld2_generated_deltaoctachrome0122.cc \
  cld2_generated_distinctoctachrome0122.cc  cld_generated_score_quad_octa_0122_2.cc  \
  -o libcld2.so

g++ -shared -fPIC -O2\
  cldutil.cc cldutil_shared.cc compact_lang_det.cc compact_lang_det_hint_code.cc \
  compact_lang_det_impl.cc  debug.cc fixunicodevalue.cc \
  generated_entities.cc  generated_language.cc generated_ulscript.cc  \
  getonescriptspan.cc lang_script.cc offsetmap.cc  scoreonescriptspan.cc \
  tote.cc utf8statetable.cc  \
  cld_generated_cjk_uni_prop_80.cc cld2_generated_cjk_compatible.cc  \
  cld_generated_cjk_delta_bi_32.cc generated_distinct_bi_0.cc  \
  cld2_generated_quad0122.cc cld2_generated_deltaocta0122.cc \
  cld2_generated_distinctocta0122.cc  cld_generated_score_quad_octa_0122.cc  \
  -o libcld2_full.so

# Build standalone executable
g++ compact_lang_det_test.cc -I. -L. libcld2_full.so \
  -o cld2

# Install (copy) required files to respective locations
#
install -D -m 0755 cld2 /usr/local/bin/cld2
install -D -m 0755 libcld2.so /usr/lib/libcld2.so
install -D -m 0755 libcld2_full.so /usr/lib/libcld2_full.so

# Copy header files
mkdir -p /usr/include/cld2/internal
mkdir -p /usr/include/cld2/public
#( cd internal
  cp generated_language.h generated_ulscript.h integral_types.h lang_script.h /usr/include/cld2/internal
#)
#( cd public
  cp ../public/compact_lang_det.h ../public/encodings.h /usr/include/cld2/public
#)

# Affected locations (in case of manual uninstall)
# rm -f /usr/local/bin/cld2
# rm -f /usr/lib/libcld2.so
# rm -f /usr/lib/libcld2_full.so
# rm -rf /usr/include/cld2/internal
# rm -rf /usr/include/cld2/public
# rm -rf /usr/include/cld2